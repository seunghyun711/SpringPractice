package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    // MeemberService에서 사용하는 MemoryMemberRepository와 테스트 케이스에서 만든 MemoryMemberRepository는 서로 다른 인스턴스다.
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        // 이렇게 하면 같은 MemoryMemberRepository를 사용하게 된다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }

    @AfterEach // 메서드가 실행이 끝날때마다 동작하는 메서드, 콜백 메서드
    public void afterEach(){
        memberRepository.clearStore(); // 메서드가 끝날 때마다 저장된 데이터를 지운다.
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring"); // 만약 spring이라면 값이 누적되어 테스트 오류 발생 따라서 클리어 해줘야 함 -> afterEah()

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// IllegalStateException예외가 발생해야 테스트가 정상 동작

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*       try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
 */       }

        //then


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}