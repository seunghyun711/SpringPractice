package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //jpa를 쓰려면(데이터를 저장하거나 변경할 때) 항상 @Transactional이 있어야한다.
//@Service // 이걸 붙여줘야 MemberService가 스프링 컨테이너에 MemberService가 등록이 된다. 그렇지 않으면 MemberService는 그냥 순수 자바 클래스일 뿐이다.
public class MemberService {
    private final MemberRepository memberRepository;

    // 생성자 주입
    public MemberService(MemberRepository memberRepository) {
        // MemberService 입장에서는 매개변수 memberRepository를 외부에서 넣어준다. 이것을 dependency injection이라 한다.
        this.memberRepository = memberRepository;
    }

    /**
     회원가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
