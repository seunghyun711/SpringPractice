package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 모든 테스트는 순서와 관계없이 메서드 별로 따로 동작하게 만들어야 한다. 순서 의존적으로 테스트를 작성하면 안된다.
// 따라서 테스트가 끝나면 클리어하여 다음 테스트에 영향을 미치지 않게 만든다.

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드가 실행이 끝날때마다 동작하는 메서드, 콜백 메서드
    public void afterEach(){
        repository.clearStore(); // 메서드가 끝날 때마다 저장된 데이터를 지운다.

    }

    @Test// 테스트로서 실행할 수 있다.
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member)); 매번 문자로 결과를 확인할 수 없다.
        // 그래서 사용하는 코드
        assertThat(member).isEqualTo(result); // 저장한 멤버가 result랑 같냐를 확인한다. 결과로 출력되는 것은 없지만 녹색 체크가 결과로 나온다.

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
