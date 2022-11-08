package study.study_MVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.study_MVC.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
        Long>, MemberRepository {
    Optional<Member> findByName(String name);
}
