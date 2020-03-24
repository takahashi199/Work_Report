package com.workreport.sample.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workreport.sample.entity.Project;
import com.workreport.sample.service.ProjectRegistService;

@Controller
public class WorkRegistController {

	@Autowired
	private ProjectRegistService service;

	@RequestMapping("/WorkRegist")
	public String registForm(Model model) {
		Project project = new Project();
		model.addAttribute("id", project.getProject_id());
		model.addAttribute("allProject", service.findAllProjects());


        return "workRegist";
    }

	@RequestMapping("/WorkRegist/Func")
	public String registfunc(
			Principal principal,
			@RequestParam String project,
			@RequestParam String client,
			@RequestParam String charge,
			@RequestParam String station,
			@RequestParam String place,
			@RequestParam String detail,
			@RequestParam String date,
			Model model
			){
		Authentication authentication = (Authentication)principal;
        String username = authentication.getName();

        Project entity = new Project();
        entity.setCreate_member(username);
        entity.setProject_id(project);
        entity.setClient(client);
        entity.setMember(charge);
        entity.setPlace(place);
        entity.setStation(station);
        entity.setDetail(detail);
        entity.setTime(date);

        try {
			//memberテーブルにinsertする。
			service.registerProject(entity);
		} catch (DataIntegrityViolationException e) {
			return "workRegist";
		}


		model.addAttribute("username", username);
		model.addAttribute("project", project);
		model.addAttribute("client", client);
		model.addAttribute("charge", charge);
		model.addAttribute("place", place);
		model.addAttribute("station", station);
		model.addAttribute("detail", detail);
		model.addAttribute("date", date);
		return "registResult";

	}




	@RequestMapping("/WorkRegist/test")
	public String testfunc(
			@RequestParam ArrayList<String> charge
			){
		System.out.println(charge);
		return "workRegist";
	}


}
