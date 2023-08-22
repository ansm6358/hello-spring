package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.service.MemberService;

public class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void join() {
		// given 무언가 주어졌을 때
		Member member = new Member();
		member.setName("spring");

		// when 이것을 실행 했을 때
		Long saveId = memberService.join(member);
		// then 나와야 하는 결과
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	void 중복_회원_예외() {
		// given 무언가 주어졌을 때
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		// when 이것을 실행 했을 때
		memberService.join(member1);

		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

//		try {
//			memberService.join(member2);
//			Assertions.fail("이미 존재하는 회원");
//		} catch (IllegalStateException e) {
//			Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//		}
		// then 나와야 하는 결과

	}

	@Test
	void findMembers() {

	}

	@Test
	void findOne() {

	}
}
