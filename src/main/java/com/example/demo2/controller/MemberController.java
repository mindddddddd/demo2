package com.example.demo2.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo2.dto.MemberDTO;
import com.example.demo2.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // Simple Logging Facade for Java

public class MemberController {

	@Autowired
	// 이 주석문 꼭 있어야됨. 이렇게하면 자동으로 객체가 생성됨! @AllArgsConstructor 써도된다.
	private MemberService memberService; // 멤버변수 선언

	
	// 회원리스트
	@RequestMapping("/member/list") // -> http://localhost:9292/member/list (사용자가 이렇게 요청을 함)
	public ModelAndView memberList() {
		log.info("========================== MemberController(/member/list) ==================================");
		ModelAndView mv=new ModelAndView("/member/list"); // 다음화면 : list.html
		List<MemberDTO> list=memberService.findMemberList(); // 위의 @Autowired 부분에서 memberService 객체를 생성한다.
		mv.addObject("list", list); 
		return mv;
	}
	
	// 회원상세정보(한명보기)
	@RequestMapping("/member/detail")
	public ModelAndView memberDetail(
			@RequestParam String id  // 파라미터가 날아옴. @RequestParam을 이용해서 id를 받아온다.
		) {
		log.info("========================== MemberController(/member/detail) ==================================");
		ModelAndView mv=new ModelAndView("/member/detail"); // 다음화면 : detail.html
		MemberDTO member=memberService.findMemberDetail(id);
		mv.addObject("member", member);
		return mv;
	}
	
	@RequestMapping("/")
	public ModelAndView main() {
		log.info("========================== MemberController(/) ==================================");
		ModelAndView mv=new ModelAndView("/main"); // 다음화면 : main.html
		return mv;  // DB갈 필요 없음
	}
	
	// 회원가입폼
	@RequestMapping("/member/register-form")
	public ModelAndView registerForm() {
		log.info("========================== MemberController(/member/register-form) ==================================");
		ModelAndView mv=new ModelAndView("/member/registerForm"); // 다음화면 : registerForm.html
		return mv; // DB갈 필요 없음
	}
	
	// 회원 추가
	@RequestMapping("/member/register")
	public ModelAndView registerMember(
			MemberDTO memberDTO, HttpServletRequest request
	) {
		log.info("========================== MemberController(/member/register) ==================================");
		memberService.registerMember(memberDTO);
		ModelAndView mv=new ModelAndView("redirect:" + request.getContextPath() + "/member/list"); 
		                              // "redirect:" +       demo2              + "/member/list"
		return mv;
	}
	
	// 회원 삭제
	@RequestMapping("/member/remove")
	public ModelAndView RemoveMember(
			@RequestParam String id, HttpServletRequest request
		) {
		log.info("========================== MemberController(/member/register) ==================================");
		memberService.removeMember(id);
		ModelAndView mv=new ModelAndView("redirect:" + request.getContextPath() + "/member/list"); 
									  // "redirect:" +       demo2              + "/member/list"
		return mv;
	}
}