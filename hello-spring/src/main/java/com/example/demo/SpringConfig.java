package com.example.demo;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.JdbcMemberRepository;
import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.service.MemberService;

@Configuration
public class SpringConfig {

//	private DataSource dataSource;
//	
//	@Autowired
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	
//	private EntityManager em;
//	
//	@Autowired
//	public SpringConfig(EntityManager em) {
//		this.em = em;
//	}
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
//	@Bean
//	public MemberRepository memberRepository() {
//		//return new MemoryMemberRepository();
//		//return new JdbcMemberRepository(dataSource);
//		//return new JdbcTemplateMemberRepository(dataSource);
//		//return new JpaMemberRepository(em);
//		
//	}
}
