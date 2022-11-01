package study.study_MVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.study_MVC.repository.JdbcMemberRepository;
import study.study_MVC.repository.MemberRepository;
import study.study_MVC.repository.MemoryMemberRepository;
import study.study_MVC.service.MemberService;

import javax.sql.DataSource;


/**
 *
 * 여기서는 향후 메모리 리포지토리를 다른 리포지토리로 변경할 예정이므로, 컴포넌트 스캔 방식 대신에
 * 자바 코드로 스프링 빈을 설정하겠다.
 *
 *
 * 회원 서비스와 회원 리포지토리의 @Service, @Repository, @Autowired 애노테이션을 제거하고
 * 진행한다
 *
 * 참고: XML로 설정하는 방식도 있지만 최근에는 잘 사용하지 않으므로 생략한다.
 * > 참고: DI에는 필드 주입, setter 주입, 생성자 주입 이렇게 3가지 방법이 있다. 의존관계가 실행중에
 * 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.
 * > 참고: 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
 * 그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로
 * 등록한다
 *
 * 주의: @Autowired 를 통한 DI는 helloController , memberService 등과 같이 스프링이 관리하는
 * 객체에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.
 * > 스프링 컨테이너, DI 관련된 자세한 내용은 스프링 핵심 원리 강의에서 설명한다
 *
 */
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
