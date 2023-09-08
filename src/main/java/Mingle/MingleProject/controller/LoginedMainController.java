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
        System.out.println("메인페이지_아이디_getAttribute 확인 = "+userId);

        List<Gathering> mingles = gatheringService.findMyMingles(userId);

        model.addAttribute("mingles",mingles);
        System.out.println("메인페이지_내모임(mingles) 확인 = "+ mingles);

        return ResponseEntity.ok(mingles);
    }

/*    @GetMapping("search2/searchMingle")
    public ResponseEntity<List<Gathering>> searchMingle(String selectedRegi,String mainCtName,String subC, Model model) {
        System.out.println("selectedRegi 확인 = "+ selectedRegi + "mainCtName 확인 = "+mainCtName+"subC 확인"+subC);
        if(subC.equals("전체")){
            List<Gathering> searchMingle = gatheringService.searchMingleCase2(selectedRegi ,mainCtName);
            System.out.println("searchMingle 확인 = "+ searchMingle);
            model.addAttribute("searchMingle",searchMingle);
            return ResponseEntity.ok(searchMingle);
        }else {
            List<Gathering> searchMingle = gatheringService.searchMingleCase1(selectedRegi, mainCtName, subC);
            System.out.println("searchMingle 확인 = "+ searchMingle);
            model.addAttribute("searchMingle",searchMingle);
            return ResponseEntity.ok(searchMingle);
        }
    }*/
    /*모임*/
    @GetMapping("search2/searchMingle")
    public ResponseEntity<List<Gathering>> searchMingle(String selectedRegi,String mainCtName,String subC, Model model) {
        System.out.printf("검색페이지_selectedRegi 확인 = %s  mainCtName 확인 = %s  subC 확인 = %s \n",selectedRegi,mainCtName,subC);

        if (selectedRegi.equals("전체")&&!mainCtName.equals("전체")&&!subC.equals("전체")||
                !selectedRegi.equals("전체")&&mainCtName.equals("전체")&&!subC.equals("전체")||
                !selectedRegi.equals("전체")&&!mainCtName.equals("전체")&&subC.equals("전체")){
            /*1개의 조건이 있을때*/System.out.printf("1개의 조건이 있을때 = %s %s %s \n",selectedRegi, mainCtName, subC);
            List<Gathering> searchMingle = gatheringService.searchMingleCase3(selectedRegi, mainCtName,subC);
            System.out.println("검색페이지_searchMingle 확인 = " + searchMingle);
            model.addAttribute("searchMingle", searchMingle);
            return ResponseEntity.ok(searchMingle);
        }else if (selectedRegi.equals("전체")&&mainCtName.equals("전체")&&!subC.equals("전체")||
                !selectedRegi.equals("전체")&&mainCtName.equals("전체")&&subC.equals("전체")||
                selectedRegi.equals("전체")&&!mainCtName.equals("전체")&&subC.equals("전체")){
            /*2개의 조건이 있을때*/System.out.printf("2개의 조건이 있을때 = %s %s %s \n",selectedRegi, mainCtName, subC);
            List<Gathering> searchMingle = gatheringService.searchMingleCase2(selectedRegi, mainCtName,subC);
            System.out.println("검색페이지_searchMingle 확인 = " + searchMingle);
            model.addAttribute("searchMingle", searchMingle);
            return ResponseEntity.ok(searchMingle);
        }else if(selectedRegi.equals("전체")&&mainCtName.equals("전체")&&subC.equals("전체")){
            /*전체 검색*/
            System.out.printf("전체 검색 = %s %s %s \n ",selectedRegi, mainCtName, subC);
            List<Gathering> searchMingle =gatheringService.searchAll();
            return ResponseEntity.ok(searchMingle);
        }else{
            /*모든 조건이 있을때*/
            System.out.printf("모든 조건이 있을때 = %s %s %s \n ",selectedRegi, mainCtName, subC);
            List<Gathering> searchMingle = gatheringService.searchMingleCase1(selectedRegi, mainCtName, subC);
            System.out.println("검색페이지_searchMingle 확인 = "+ searchMingle);
            model.addAttribute("searchMingle",searchMingle);

            return ResponseEntity.ok(searchMingle);
        }
    }

    @GetMapping("search2/searchName")
    public ResponseEntity<List<Gathering>> searchName(String searchName, Model model) {
            List<Gathering> searchMingle = gatheringService.searchName(searchName);
            System.out.println("searchMingle 확인 = "+ searchMingle);
            model.addAttribute("searchMingle",searchMingle);
            return ResponseEntity.ok(searchMingle);

    }


}









