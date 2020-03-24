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
public class WorkEditController {

	@Autowired
	private ProjectRegistService service;

	@RequestMapping("/WorkEdit")
	public String Edit(@RequestParam String id,
			Model model) {
		Project project = service.findProject(id);
		String members = project.getMember();
		ArrayList<String> member = new ArrayList<>();

		//登録されているメンバーを配列型に変換
		while(true) {
		int search = members.indexOf(",");
		if (search != -1) {
			member.add(members.substring(0, (search)));
			members = members.substring(search + 1);

		}else {
			member.add(members);
			break;
		}
		}

		model.addAttribute("id", project.getId());
		model.addAttribute("project_id", project.getProject_id());
		model.addAttribute("time", project.getTime());
		model.addAttribute("client", project.getClient());
		model.addAttribute("member", member);
		model.addAttribute("place", project.getPlace());
		model.addAttribute("station", project.getStation());
		model.addAttribute("detail", project.getDetail());

		System.out.println(project.getDetail());

		 return "workEdit";
	}

	@RequestMapping("/WorkEdit/Updata")
	public String WorkUpdata(
			Principal principal,
			@RequestParam String regi_id,
			@RequestParam String project,
			@RequestParam String client,
			@RequestParam String charge,
			@RequestParam String station,
			@RequestParam String place,
			@RequestParam String detail,
			@RequestParam String date,
			Model model) {

		Authentication authentication = (Authentication)principal;
        String username = authentication.getName();

        Project entity = new Project();

		entity.setCreate_member(username);
//		entity.setId(Integer.parseInt(regi_id));
        entity.setProject_id(project);
        entity.setClient(client);
        entity.setMember(charge);
        entity.setPlace(place);
        entity.setStation(station);
        entity.setDetail(detail);
        entity.setTime(date);

//        System.out.println(regi_id);
//        System.out.println("controller ok");

        try {
			//projectテーブルにinsertする。
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
}
