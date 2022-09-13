package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SpringConfig {

//    private final EntityManager em;
    // 스프링부트가 DataSource를 만든다.
//    private final DataSource dataSource;

    // 스프링 데이터 jpa 사용
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em){
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    @Bean // 스프링 빈으로 등록한다는 의미 MemberService가 스프링 빈에 등록된다.
    public MemberService  memberService(){
 //       return new MemberService(memberRepository()); // 스프링 빈에 등록된 MemberRepository를 MemberService에 넣어준다.
        return new MemberService(memberRepository);
    }

    @Bean // aop를 스프링 빈으로 등록
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }

    // MemberService에는 MemberRepository가 필요하다.
//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository(); 이전까지 사용했던 스프링 빈에 등록하여 사용하는 방식
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);



//    }
}
