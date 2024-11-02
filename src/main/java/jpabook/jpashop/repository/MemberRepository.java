package jpabook.jpashop.repository;
import jpabook.jpashop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //자동으로 스프링 bean으로 관리
@RequiredArgsConstructor
public class MemberRepository {


    private final EntityManager em; //스프링이 만들어서 주입해줌

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        //엔티티 멤버를 조회하는 느낌
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
