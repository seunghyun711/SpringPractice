package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository // 스프링이 리포지토리를 가져온다.
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 메모리 저장할 장소
    private static long sequence = 0L; // 0,1,2 같은 키 값을 생성해주는 역할

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 널이 반환될 가능성이 있기 때문에 이렇게 사용한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 찾으면 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 멤버들이 반환이 된다.
    }

    public void clearStore(){
        store.clear(); // 스토어를 싹 비운다.
    }
}
