package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // 웹 애플리케이션에서 /hello라고 들어오면 hello메서드를 호출
    public String hello(Model model){ // 스프링이 model을 만들어 넣어준다.
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name, Model model){// 외부에서 파라미터를 받는다
        model.addAttribute("name",name); // "name"은 key
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // @ResponseBody 를 사용하면 뷰 리졸버( viewResolver )를 사용하지 않음 대신에 HTTP의 BODY에 문자 내용을 직접 반환(HTML BODY TAG를 말하는 것이 아님
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // 이 문자 그대로 전달된다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello hellpApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
