package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	/*
	 * 회원 가입
	 */

	public Long join(Member member) {

//		Optional<Member> result = memberRepository.findByName(member.getName());
//		
//		//result.orElseGet(); 값이 있으면 꺼내고 아니면 다른 것을 실행
//		result.ifPresent(m -> {
//			throw new IllegalStateException("이미 존재하는 회원입니다");
//		});

		validateDuplicateMember(member);

		memberRepository.save(member);
		return member.getId();

	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName()).ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다");
		});
	}

	// 전체 회원 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();

	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
