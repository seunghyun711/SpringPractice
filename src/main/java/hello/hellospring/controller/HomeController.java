package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // /는 도메인 호출될 때 첫 화면
    public String home(){
        return "home"; // home.html이 호출됨
    }
}
