package Mingle.MingleProject.controller;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.repository.MemberRepository;
import Mingle.MingleProject.service.CityService;
import Mingle.MingleProject.service.MemberService;
import Mingle.MingleProject.service.RegisterMail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MingleController {
    //생성자 주입
    private final MemberService memberService ;
    private final CityService cityService ;

    // 회원가입 메일 서비스
    @Autowired
    RegisterMail registerMail;
    private final MemberRepository memberRepository;


    //기본페이지 요청메소드
    @GetMapping("/")
    public String index(){
        return "Main_UnLogIn";
    }

    @GetMapping("login")
    public String loginForm() { return "login"; }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Main_UnLogIn";
    }

    @GetMapping("find_id*")
    public String find_id() { return "find_id"; }

    @GetMapping("/find_id/checkMember")
    @ResponseBody
    public String checkMember(@RequestParam("mEmail") String mEmail, @RequestParam("mName") String mName) {
        // 여기에서 데이터베이스에서 이메일과 mName으로 검색하여 일치하는 레코드가 있는지 확인
        boolean existsInDatabase = memberService.emailExistsInDatabase(mEmail, mName);
        if (existsInDatabase ) {
            return "recordExists";
        } else {
            return "recordNotFound";
        }
    }

    @GetMapping("/find_pw/checkMember")
    @ResponseBody
    public String checkMemberPw(@RequestParam("mEmail") String mEmail, @RequestParam("mId") String mId) {
        // 여기에서 데이터베이스에서 이메일과 mName으로 검색하여 일치하는 레코드가 있는지 확인
        boolean existsInDatabase = memberService.emailExistsInDatabasePw(mEmail, mId);
        if (existsInDatabase ) {
            return "recordExists";
        } else {
            return "recordNotFound";
        }
    }

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

    @GetMapping("/Main_LogIn")
    public String Main_LogIn() { return "Main_LogIn"; }

    @GetMapping("myClass*")
    public String myClass() {return "myClass";}

    @GetMapping("schedule*")
    public String schedule() {return "schedule";}

    @GetMapping("MyPage*")
    public String MyPage(){return "MyPage";}

    @GetMapping("Create_Meet")
    public String Create_Meet(Model model) {
        List<String> bcNames = cityService.getDistinctBcNames();

        model.addAttribute("bcNames", bcNames);
        return "Create_Meet";
    }

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
    public String search2() {return "search2";}
    @GetMapping("selectRegi")
    public String selectRegi() {
        return "selectRegi";
    }

    @GetMapping("/delete/{mId}")
    public String deleteById(@PathVariable("mId") String mID, RedirectAttributes redirectAttributes, HttpSession session) {
        boolean deleted = memberService.deleteMemberById(mID);
        if (deleted) {
            // 세션 무효화 (세션 종료)
            session.invalidate();
            // 로그아웃 메시지 추가
        } else {
            // 회원 삭제가 실패한 경우
            redirectAttributes.addFlashAttribute("error", "fail");
        }
        return "redirect:/Main_UnLogIn?message=success";
    }

    @PostMapping("login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        MemberDTO loginResult = memberService.login(memberDTO);
        System.out.println(loginResult);
        if(loginResult != null) {
            //login 성공
            session.setAttribute("loginId", loginResult.getMId());
            session.setAttribute("memberDTO", loginResult);
            model.addAttribute("memberDTO",loginResult);
            return "Main_LogIn";
        }else {
            //login 실패
            model.addAttribute("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "/login";
        }
    }

    @PostMapping("join")
    public String save(@ModelAttribute MemberDTO memberDTO) {
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

    // 이메일 인증코드 발송
    @PostMapping("/find_id/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestParam("mEmail") String mEmail) throws Exception {

        String code = registerMail.sendSimpleMessage(mEmail);
        System.out.println("인증코드 : " + code);
        return code;
    }


    @PostMapping("/find_id/findMId")
    public ResponseEntity<String> findMemberId(@RequestParam String mName, @RequestParam String mEmail) {
        // mName과 mEmail을 기반으로 mId를 조회하는 로직을 구현하세요.
        // 실제로는 데이터베이스에서 해당 회원을 찾고 mId를 반환합니다.

        String memberId = memberRepository.findMemberIdByNameAndEmail(mName, mEmail); // 회원 아이디를 조회하는 메서드 구현 필요

        if (memberId != null) {
            return ResponseEntity.ok(memberId);
        } else {
            return ResponseEntity.notFound().build(); // 회원을 찾지 못한 경우 404 응답 반환
        }
    }

    @PostMapping("/find_id/findMPw")
    public ResponseEntity<String> findMemberPw(@RequestParam String mId, @RequestParam String mEmail) {
        // mId mEmail을 기반으로 mPw를 조회하는 로직을 구현하세요.
        // 실제로는 데이터베이스에서 해당 회원을 찾고 mId를 반환합니다.

        String memberPw = memberRepository.findMemberPwdByIdAndEmail(mId, mEmail); // 회원 아이디를 조회하는 메서드 구현 필요

        if (memberPw != null) {
            return ResponseEntity.ok(memberPw);
        } else {
            return ResponseEntity.notFound().build(); // 회원을 찾지 못한 경우 404 응답 반환
        }
    }

    @PostMapping("/Mypage/mIntroduction")
    public String introduce(@RequestParam("mIntroduction") String mIntroduction) {
        System.out.println("mIntroduction : " + mIntroduction);
        memberService.introduce(mIntroduction);
        return "Mypage"; // 결과 페이지로 이동

    }

    @PostMapping("/Mypage/uploadImage")
    public String uploadImage(@RequestParam("mProfileimg") MultipartFile mProfileimg) {
        System.out.println("mProfileimg : " + mProfileimg);
        memberService.uploadImage(mProfileimg);
        return "Mypage";
    }


}







