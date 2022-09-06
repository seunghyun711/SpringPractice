package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스만 만들면 스프링 데이터 jpa가 인터페이스에 대한 구현체를 알아서 만든다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
