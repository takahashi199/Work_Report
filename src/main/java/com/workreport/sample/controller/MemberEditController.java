package com.workreport.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workreport.sample.entity.Member;
import com.workreport.sample.service.MemberRepositoryService;

@Controller
public class MemberEditController {

	@Autowired
	private MemberRepositoryService service;

	@RequestMapping("/MemberList")
	public String memberlist(Model model) {

		model.addAttribute("Members",service.findRoleMember());
		model.addAttribute("Registers", service.findRoleRegister());

		return "memberslist";

	}

	@RequestMapping("/MemberList/RoleAdd")
	public String RoleAdd(
			@RequestParam(name="add", defaultValue="none") String add,
			@RequestParam(name="del", defaultValue="none") String del,
			Model model) {

		if(!(add.equals("none"))) {
			Member member = service.addAuthority(add);
		}else if(!(del.equals("none"))) {
			Member member = service.delMember(del);
		}

		model.addAttribute("Members",service.findRoleMember());
		model.addAttribute("Registers", service.findRoleRegister());

		return "memberslist";
	}

	@RequestMapping("/MemberList/RoleDel")
	public String RoleDel(
			@RequestParam(name="add", defaultValue="none") String add,
			@RequestParam(name="del", defaultValue="none") String del,
			Model model) {

		if(!(add.equals("none"))) {
			Member member = service.delAuthority(add);
		}else if(!(del.equals("none"))) {
			Member member = service.delMember(del);
		}

		model.addAttribute("Members",service.findRoleMember());
		model.addAttribute("Registers", service.findRoleRegister());

		return "memberslist";
	}
}
