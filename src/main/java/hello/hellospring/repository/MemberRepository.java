package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // member의 id로 회원을 찾을 것이다.
    Optional<Member> findByName(String nmae);
    List<Member> findAll(); // 지금까지 저장한 모든 회원리스트 반환
}
