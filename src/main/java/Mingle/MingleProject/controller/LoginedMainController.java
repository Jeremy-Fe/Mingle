package Mingle.MingleProject.controller;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.service.GatheringService;
import Mingle.MingleProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginedMainController {
    //생성자 주입
    private final MemberService memberService;
    private final GatheringService gatheringService;

//    @CrossOrigin
    @GetMapping("Main_LogIn/findMyMingles")
    public ResponseEntity<List<Gathering>> getMeetings(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("loginId");
        System.out.println("getAttribute 확인 = "+userId);

        List<Gathering> mingles = gatheringService.findMyMingles(userId);

        model.addAttribute("mingles",mingles);
        System.out.println("mingles 확인 = "+ mingles);

        return ResponseEntity.ok(mingles);
    }
//    @GetMapping("search2/searchMingle")
//    public ResponseEntity<List<Gathering>> searchMingle(String selectedRegi,String mainCtName,String subC, Model model) {
//        List<Gathering> searchMingle = gatheringService.searchMingleMingles(selectedRegi ,mainCtName, subC);
//
//        model.addAttribute("searchMingle",searchMingle);
//        System.out.println("searchMingle 확인 = "+ searchMingle);
//
//        return ResponseEntity.ok(searchMingle);
//    }


}









