package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // 스프링 빈으로 등록한다는 의미 MemberService가 스프링 빈에 등록된다.
    public MemberService  memberService(){
        return new MemberService(memberRepository()); // 스프링 빈에 등록된 MemberRepository를 MemberService에 넣어준다.
    }

    // MemberService에는 MemberRepository가 필요하다.
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
