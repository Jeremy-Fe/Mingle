package Mingle.MingleProject.controller;

import Mingle.MingleProject.config.MemberComparator;
import Mingle.MingleProject.dto.GatheringDTO;
import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.dto.PostDTO;
import Mingle.MingleProject.entity.PostEntity;
import Mingle.MingleProject.service.CityService;
import Mingle.MingleProject.service.GatheringService;
import Mingle.MingleProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GatheringController {
    //생성자 주입
    private final MemberService memberService;
    private final CityService cityService;
    private final GatheringService gatheringService;


    @GetMapping("Gathering_Home/{id}")
    public String Gathering_Home(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        List<MemberDTO> gatheringMemberDTO = memberService.findByGatheringMember(gatheringDTO.getGName());

        // 객체 정렬 (모임장 1순위 운영진 2순위 회원 3순위 오름차순 정렬
        MemberComparator memberComparator = new MemberComparator(gatheringDTO);
        Collections.sort(gatheringMemberDTO, memberComparator);
        model.addAttribute("GatheringMember", gatheringMemberDTO);

        int gatheringHeadcount = memberService.findByGatheringHeadcount(gatheringDTO.getGName());
        model.addAttribute("headcount", gatheringHeadcount);

        return "Gathering_Home";
    }

//    @GetMapping("Gathering_Board/{id}")
//    public String Gathering_Board(@PathVariable Long id, Model model) {
//        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
//        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
//        model.addAttribute("GatheringHome", gatheringDTO);
//
//        List<PostDTO> postDTOList = gatheringService.findByNotificationPost(id);
//
//
//        return "Gathering_Board";}
//
//        return "Gathering_Board";
//    }

    @GetMapping("Gathering_Post/{id}")
    public String Gathering_Post(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);


        return "Gathering_Post";
    }

    @GetMapping("Gathering_Album_All/{id}")
    public String Gathering_Album_All(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);


        return "Gathering_Album_All";
    }

    @GetMapping("Gathering_Album_Board/{id}")
    public String Gathering_Album_Board(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);


        return "Gathering_Album_Board";
    }

    @GetMapping("Gathering_Album_BoardReview/{id}")
    public String Gathering_Album_BoardReview(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);


        return "Gathering_Album_BoardReview";
    }

    @GetMapping("Gathering_Album_BoardFree/{id}")
    public String Gathering_Album_BoardFree(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);


        return "Gathering_Album_BoardFree";
    }

    @GetMapping("Gathering_Album_BoardShareInterest/{id}")
    public String Gathering_Album_BoardShareInterest(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);


        return "Gathering_Album_BoardShareInterest";
    }

    @GetMapping("Gathering_Album_BoardJoin/{id}")
    public String Gathering_Album_BoardJoin(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);


        return "Gathering_Album_BoardJoin";
    }

    @GetMapping("Gathering_Album_BoardNotification/{id}")
    public String Gathering_Album_BoardNotification(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);


        return "Gathering_Album_BoardNotification";
    }

    @GetMapping("Gathering_Schedule/{id}")
    public String Gathering_Schedule(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);


        return "Gathering_Schedule";
    }

    @PostMapping("/create-gathering")
    public String save(@ModelAttribute GatheringDTO gatheringDTO){
        gatheringService.save(gatheringDTO);
        System.out.println("GatheringController.save");
        System.out.println("GatheringDTO = " + gatheringDTO);
        return "myClass";
    }

    @GetMapping("Gathering_Post_Write")
    public String Gathering_Post_Write(){
        return "Gathering_Post_Write";
    }

}
