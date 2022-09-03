package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 스프링 컨테이너라는 스프링 통에 컨트롤러 애너테이션이 있으면 MemberController라는 객체를 생성해 스프링에 넣어두고 관리한다, 이를 스프링 빈이 관리된다고 표현한다.
public class MemberController {
    private final MemberService memberService;
    /*
    스프링이 관리를 하게 되면 다 스프링 컨테이너에 등록하고 스프링 컨테이너에 받아서 쓰도록 관리해야 한다.
    만약 new로 객체 생성해서 사용하면 멤버 컨트롤러 말고도 다른 여러 서버스에서도 가져다 쓸 수 있다. 이러면 new롤 객체를 생성하여 그 객체에 있는 필요없는
    인스턴스까지 생성하게 되는 것이다. 따라서 스프링 컨테이너에 등록하여 쓴다. 하나만 생성해서 공용으로 쓰면 된다.
     */
    @Autowired // MemberController를 생성할 때 memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져다가 연결한다. => 의존관계 주입
    // 생성자를 통해 MemberService가 MemberController에 주입된다 -> 생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 필드 주입
//    @Autowired private MemberService memberService;


    //setter 주입 => 누군가 MemberController 호출 시 setMemberService가 public으로 열려있어야 한다. 즉, public으로 노출되어 변경되는 등의 위험에 노출된다.
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
