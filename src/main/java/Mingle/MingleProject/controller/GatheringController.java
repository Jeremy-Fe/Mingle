package Mingle.MingleProject.controller;

import Mingle.MingleProject.config.MemberComparator;
import Mingle.MingleProject.dto.GatheringDTO;
import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.dto.PostDTO;
import Mingle.MingleProject.service.CityService;
import Mingle.MingleProject.service.GatheringService;
import Mingle.MingleProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
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


        int gatheringHeadcount = gatheringMemberDTO.size();
        model.addAttribute("headcount", gatheringHeadcount);

        List<PostDTO> postDTOList = gatheringService.findByPosts(id);
        List<PostDTO> postDTO2List = postDTOList.subList(0, 2);
        model.addAttribute("Post", postDTO2List);

        model.addAttribute("PostBoard", BoardName(postDTO2List));

        List<MemberDTO> writerList = new ArrayList<>();
        for (PostDTO postDTO: postDTOList) {
            writerList.add(memberService.findByWriter(postDTO.getPMId()));
        }
        List<MemberDTO> writer2List = writerList.subList(0, 2);
        model.addAttribute("PostWriter", writer2List);

        return "Gathering_Home";
    }

    @GetMapping("Gathering_Board/{id}")
    public String Gathering_Board(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        List<PostDTO> postDTONotiList = gatheringService.findByNotificationPost(id);
        model.addAttribute("Notification", postDTONotiList);

        List<PostDTO> postDTOList = gatheringService.findByPosts(id);
        model.addAttribute("Post", postDTOList);

        model.addAttribute("PostBoard", BoardName(postDTOList));

        List<MemberDTO> writerList = new ArrayList<>();
        for (PostDTO postDTO: postDTOList) {
            writerList.add(memberService.findByWriter(postDTO.getPMId()));
        }
        model.addAttribute("PostWriter", writerList);

        return "Gathering_Board";
    }



    @GetMapping("Gathering_Post/{id}/{pNum}")
    public String Gathering_Post(@PathVariable Long id, @PathVariable Long pNum, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        PostDTO postDTO = gatheringService.findByPost(pNum);
        model.addAttribute("post", postDTO);

        model.addAttribute("boardName", BoardName(postDTO));

        MemberDTO memberDTO = memberService.findByWriter(postDTO.getPMId());
        model.addAttribute("writer", memberDTO);

        return "Gathering_Post";
    }

    @GetMapping("Gathering_Album_All/{id}")
    public String Gathering_Album_All(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        
        return "Gathering_Album_All";}

    @GetMapping("Gathering_Album_Board/{id}")
    public String Gathering_Album_Board(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        
        return "Gathering_Album_Board";}

    @GetMapping("Gathering_Album_BoardReview/{id}")
    public String Gathering_Album_BoardReview(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        
        return "Gathering_Album_BoardReview";}

    @GetMapping("Gathering_Album_BoardFree/{id}")
    public String Gathering_Album_BoardFree(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        
        return "Gathering_Album_BoardFree";}

    @GetMapping("Gathering_Album_BoardShareInterest/{id}")
    public String Gathering_Album_BoardShareInterest(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        
        return "Gathering_Album_BoardShareInterest";}

    @GetMapping("Gathering_Album_BoardJoin/{id}")
    public String Gathering_Album_BoardJoin(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        
        return "Gathering_Album_BoardJoin";}

    @GetMapping("Gathering_Album_BoardNotification/{id}")
    public String Gathering_Album_BoardNotification(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        
        return "Gathering_Album_BoardNotification";}

    @GetMapping("Gathering_Schedule/{id}")
    public String Gathering_Schedule(@PathVariable Long id, Model model) {
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        
        return "Gathering_Schedule";}

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

    public List BoardName(List<PostDTO> list) {
        List boardName = new ArrayList();
        for (PostDTO postDTO : list) {
            Long bNum = postDTO.getPBNum();
            if(bNum == 1L){
                boardName.add("정모 후기");
            } else if(bNum == 2L) {
                boardName.add("자유게시판");
            } else if(bNum == 3L) {
                boardName.add("관심사 공유");
            } else if(bNum == 4L) {
                boardName.add("가입인사");
            } else if(bNum == 5L) {
                boardName.add("공지사항");
            }

        }

        return boardName;
    }
    public String BoardName(PostDTO board) {
        Long bNum = board.getPBNum();
        String boardName = null;
        if(bNum == 1L){
            boardName = "정모후기";
        } else if(bNum == 2L) {
            boardName = "자유게시판";
        } else if(bNum == 3L) {
            boardName = "관심사 공유";
        } else if(bNum == 4L) {
            boardName = "가입인사";
        } else if(bNum == 5L) {
            boardName = "공지사항";
        }

        return boardName;
    }

}
