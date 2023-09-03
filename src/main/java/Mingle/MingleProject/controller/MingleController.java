package Mingle.MingleProject.controller;

import Mingle.MingleProject.dto.CityDTO;
import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.CityEntity;
import Mingle.MingleProject.service.CityService;
import Mingle.MingleProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("join")
    public String showCities(Model model) {
        List<String> bcNames = cityService.getDistinctBcNames();

        model.addAttribute("bcNames", bcNames);
        return "join";
    }

    @GetMapping("Main_UnLogIn*")
    public String Main_UnLogIn() {
        return "Main_UnLogIn";}

    @GetMapping("Main_LogIn*")
    public String Main_LogIn() { return "Main_LogIn"; }

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

    @GetMapping("Gathering_Post")
    public String Gathering_Post() {return "Gathering_Post";}

    @GetMapping("Gathering_Album_All")
    public String Gathering_Album_All() {return "Gathering_Album_All";}

    @GetMapping("Gathering_Album_Board")
    public String Gathering_Album_Board() {return "Gathering_Album_Board";}

    @GetMapping("Gathering_Album_BoardReview")
    public String Gathering_Album_BoardReview() {return "Gathering_Album_BoardReview";}

    @GetMapping("Gathering_Album_BoardFree")
    public String Gathering_Album_BoardFree() {return "Gathering_Album_BoardFree";}

    @GetMapping("Gathering_Album_BoardShareInterest")
    public String Gathering_Album_BoardShareInterest() {return "Gathering_Album_BoardShareInterest";}

    @GetMapping("Gathering_Album_BoardJoin")
    public String Gathering_Album_BoardJoin() {return "Gathering_Album_BoardJoin";}

    @GetMapping("Gathering_Album_BoardNotification")
    public String Gathering_Album_BoardNotification() {return "Gathering_Album_BoardNotification";}

    @GetMapping("search1")
    public String search1() {return "search1";}
    @GetMapping("search2")
    public String search2() {return "search1";}

    @GetMapping("selectResi")
    public String selectResi() {return "selectResi";}

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

    @PostMapping("/join/id-check")
    public @ResponseBody String idCheck(@RequestParam("mId") String mId) {
        System.out.println("mId = " + mId);
        String checkResult = memberService.idCheck(mId);
        return checkResult;
    }


}




