package study.study_MVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * 정적컨텐츠/MVC/API 방식
 * 1. 정적컨텐츠 : 내용 그대로 반환
 * 2. MVC : 모델을 반환
 * 3. API : 객체반환
 *
 * @ResponseBody 어노테이션이
 *
 * 없을땐 : viewResolver 동작
 * 해당 return에 있는 값에 맞는 html을 찾아준다
 *
 * 있을땐 : HttpMessageConverter 동작
 * return에 있는 객체를 json형태로 값을 반환해준다.
 * html을 찾아주는게 아님
 *
 */
@Controller
public class HelloController {


    @GetMapping("hello")
    public String hello(Model model) {
        //http://localhost:8080/hello-static.html
        //해당 controller가 없어도 spring에서 해당 html을 자동으로 찾아준다.
        model.addAttribute("data", "hello!!!");
        return "hello";//hello.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name,Model model)
    {
        //http://localhost:8080/hello-mvc?name=spring!!!
        model.addAttribute("name", name);
        return "hello-template";//hello-template.html
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        //http://localhost:8080/hello-api?name=pcy
        //json 형태로 리턴해준다.
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
