package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 엔티티
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql문에 id를 넣는 것이 아니라 db에 값을 넣으면 db가 id를 자동으로 생성해주는 것을 아이덴티티 전략이라 한다.
    private Long id; // 시스템이 데이터를 구분하기 위해 임의로 저정하는 값

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
