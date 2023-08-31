package Mingle.MingleProject.controller;

import Mingle.MingleProject.dto.CityDTO;
import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.CityEntity;
import Mingle.MingleProject.service.CityService;
import Mingle.MingleProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MingleController {
    //생성자 주입
    private final MemberService memberService;
    private final CityService cityService;

    //기본페이지 요청메소드
    @GetMapping("/")
    public String index(){
        return "Main_UnLogIn";
    }

    @GetMapping("login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("find_id*")
    public String find_id() { return "find_id"; }

    @GetMapping("find_pw*")
    public String find_pw() { return "find_pw"; }

//    @GetMapping("join")
////    public String join() { return "join"; }
//    public String showCities(Model model) {
//        List<CityDTO> cities = cityService.getAllCities();
//        System.out.println(cities);
//        model.addAttribute("cities", cities);
//        System.out.println("model = " + model);
//        return "join";
//    }

    @GetMapping("join")
//    public String join() { return "join"; }
    public String showCities(Model model) {
        List<String> bcNames = cityService.getDistinctBcNames();
        List<String> mcNames = cityService.getDistinctMcNames();

        model.addAttribute("bcNames", bcNames);
        model.addAttribute("mcNames", mcNames);
        return "join";
    }

    @GetMapping("Main_UnLogIn*")
    public String Main_UnLogIn() {
        return "Main_UnLogIn";}

    @GetMapping("Main_LogIn*")
    public String Main() { return "Main_LogIn"; }

    @GetMapping("myClass*")
    public String myClass() {return "myClass";}

    @GetMapping("schedule*")
    public String schedule() {return "schedule";}

    @GetMapping("Mypage*")
    public String Mypage() {return "Mypage";}

    @GetMapping("Create_Meet*")
    public String Create_Meet() {return "Create_Meet";}

    @GetMapping("Mbti_banner*")
    public String Mbti_banner() {return "Mbti_banner";}

    @GetMapping("Gathering_Home")
    public String Gathering_Home() {return "Gathering_Home";}

    @GetMapping("Gathering_Board")
    public String Gathering_Board() {return "Gathering_Board";}

    @PostMapping("login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        System.out.println(memberDTO);
        if(loginResult != null) {
            //login 성공
            session.setAttribute("loginId", loginResult.getMId());
            return "Main_LogIn";
        }else {
            //login 실패
            return "login";
        }
    }

    @PostMapping("join")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
//        MemberService memberService = new MemberService(); -> @RequiredArgsConstructor 이걸로 대체
        memberService.save(memberDTO);
        return "login";
    }


}




