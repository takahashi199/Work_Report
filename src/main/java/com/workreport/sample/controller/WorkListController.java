package com.workreport.sample.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.workreport.sample.entity.Project;
import com.workreport.sample.repository.MemberRepository;
import com.workreport.sample.repository.ProjectRepository;

@Controller
public class WorkListController {


		@Autowired
		MemberRepository memberRepository;

		@Autowired
		ProjectRepository projectRepository;

		@RequestMapping(value = "/worklist")
		public ModelAndView project(ModelAndView mav,
				Principal principal) {

			// viewの指定
			mav.setViewName("WorkReport");

			List<String> dates = projectRepository.getByDate();
			mav.addObject("dates",dates);

			// ログインしているidを取得しviewに送る
		/*			Authentication auth = (Authentication)principal;
					AccountDetails accountDetails = (AccountDetails)auth.getPrincipal();
					String Login_id = accountDetails.getMember().getLogin_id();
					mav.addObject("Login_id",Login_id);*/

			// projectについて
			//　place_idが一致するfootprintオブジェクト
			List<Project> projectDatalist = projectRepository.findByCurrentDate();
			mav.addObject("projectDatalist",projectDatalist);

			return mav;

		}

		@RequestMapping(value = "/info")
		public ModelAndView serchProject(ModelAndView mav,
				@RequestParam("date") String date,
				Principal principal) {

			// viewの指定
			mav.setViewName("WorkReport");

			List<String> dates = projectRepository.getByDate();
			mav.addObject("dates",dates);

			//URLからplace_idを取得しviewに送る
			mav.addObject("selectedDate",date);

			// ログインしているidを取得しviewに送る
		/*			Authentication auth = (Authentication)principal;
					AccountDetails accountDetails = (AccountDetails)auth.getPrincipal();
					String Login_id = accountDetails.getMember().getLogin_id();
					mav.addObject("Login_id",Login_id);*/

			// projectについて
			//　place_idが一致するfootprintオブジェクト
			List<Project> projectDatalist = projectRepository.findByDate(date);
			mav.addObject("projectDatalist",projectDatalist);
			return mav;

		}

}
