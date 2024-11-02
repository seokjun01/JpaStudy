package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false) //롤백안하고 커밋해서 등록쿼리 다 볼 수 있음
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("Kim");

        //when
        Long savedID = memberService.join(member);

        //then
        assertEquals(member , memberRepository.findOne(savedID));
        //제대로 저장되었는지 확인
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("Kim1");

        Member member2 = new Member();
        member2.setName("Kim1");

        // when & then
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2); // 예외가 발생해야 함
        });
    }


}