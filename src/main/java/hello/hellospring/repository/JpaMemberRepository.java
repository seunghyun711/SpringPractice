package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //jpa는 엔티티 매니저로 동작한다. jpa를 쓰려면 엔티티 매니저가 있어야 한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    //build.gradle에서 data-jpa를 라이브러리로 받으면 스프링 부트가 자동으로 엔티티 매니저를 생성한다.

    @Override
    public Member save(Member member) {
            em.persist(member);
            return member;
            // 이렇게 하면 jpa가 insert문 작성해서 db에 넣고 id까지 멤버에 setId까지 다 수행한다.
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).
                setParameter("name", name)
                .getResultList();
        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        // 객체를 대상으로 쿼리를 날리면 이것이 sql로 번역된다.
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }
}
