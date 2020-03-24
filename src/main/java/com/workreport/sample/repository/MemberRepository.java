package com.workreport.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.workreport.sample.entity.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

	@Query("FROM Member where login_id = :login_id and deleted_flag != 'true'")
	public Member findLogin_id(@Param("login_id") String login_id);

	@Query("FROM Member where authority = 'MEMBER' and deleted_flag != 'true' ORDER BY login_id ASC " )
	public List<Member> findRoleMember();

	@Query("FROM Member where Authority = 'REGISTER' and deleted_flag != 'true' ORDER BY login_id ASC ")
	public List<Member> findRoleRegister();

}
