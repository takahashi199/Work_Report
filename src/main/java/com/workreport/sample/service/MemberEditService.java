package com.workreport.sample.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workreport.sample.entity.Member;
import com.workreport.sample.repository.MemberRepository;

@Service
public class MemberEditService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private EntityManagerFactory factory;

	public Member addAuthority(String login_id) {

		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		Member entity = findMember(login_id);
		entity.setAuthority("Register");

		memberRepository.flush();
		tx.commit();
		return entity;
	}


	public Member findMember(String login_id) {
		Optional<Member> memberOpt = memberRepository.findById(login_id);

		if (memberOpt.isPresent()) {
			return memberOpt.get();
		}else {
			return null;
		}
	}

}
