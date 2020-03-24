package com.workreport.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.workreport.sample.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

	//	初期表示時

	@Query(value ="SELECT * FROM project WHERE to_char(created_at,'yyyy-mm') LIKE to_char(now(),'yyyy-mm')",nativeQuery = true)
	public List<Project> findByCurrentDate();

	//  年月で検索
	@Query(value="SELECT * FROM project WHERE to_char(created_at,'yyyymm') = :date",nativeQuery = true)
	public List<Project> findByDate(@Param("date") String date);

	//  データにある重複しない年月
	@Query(value="SELECT distinct to_char(created_at,'yyyymm') FROM project WHERE EXISTS (SELECT to_char(created_at,'yyyy-mmdd') FROM project)",nativeQuery = true)
	public List<String> getByDate();

}
