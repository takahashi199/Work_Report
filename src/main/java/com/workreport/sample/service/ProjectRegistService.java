package com.workreport.sample.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workreport.sample.entity.Project;
import com.workreport.sample.repository.ProjectRepository;

@Service
@Transactional
public class ProjectRegistService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private EntityManagerFactory factory;

	/**
	 * プロジェクト情報情報をDBに登録。
	 */


	@Transactional
	public Project registerProject(Project entity) throws DataIntegrityViolationException {


		//プロジェクト情報をprojectテーブルにinsert。
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.persist(entity);
		projectRepository.flush();
		tx.commit();

//		entity = projectRepository.save(entity);

		return entity;
	}

	//IDをもとにプロジェクト取得
	public Project findProject(String id) {
		Integer id_num = Integer.parseInt(id);
		Optional<Project> projectOpt = projectRepository.findById(id_num);

		if (projectOpt.isPresent()) {
			return projectOpt.get();
		}else {
			return null;
		}
	}

	//プロジェクト情報のアップデート
	@Transactional(readOnly = false)
	public Project updateProject(Project project) {

		//更新処理
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		System.out.println("update");
		System.out.println(project.getId());
		Project org =  manager.find(Project.class, project.getId());
		org.setCreate_member(project.getCreate_member());
		org.setProject_id(project.getProject_id());
		org.setClient(project.getClient());
		org.setMember(project.getMember());
		org.setPlace(project.getPlace());
		org.setStation(project.getStation());
		org.setTime(project.getTime());
		org.setDetail(project.getDetail());


//		if (is_edit_by_administrator) {
//			org.setAdmin_flag(member.isAdmin_flag());
//		}

		projectRepository.flush();
		tx.commit();
		return org;
	}

	//登録されている全プロジェクト取得
	public List<Project> findAllProjects() {
		return projectRepository.findAll();
	}

}
