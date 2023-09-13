package Mingle.MingleProject.controller;

import Mingle.MingleProject.config.MemberComparator;
import Mingle.MingleProject.dto.GatheringDTO;
import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.dto.PostDTO;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.repository.MemberRepository;
import Mingle.MingleProject.dto.*;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.entity.ScheduleEntity;
import Mingle.MingleProject.repository.MemberRepository;
import Mingle.MingleProject.service.CityService;
import Mingle.MingleProject.service.GatheringService;
import Mingle.MingleProject.service.MemberService;
import Mingle.MingleProject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GatheringController {
    //생성자 주입
    private final MemberService memberService;
    private final CityService cityService;
    private final GatheringService gatheringService;
    private final MemberRepository memberRepository;
    private final PostService postService;


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
        if(postDTOList.size() > 2) {
            List<PostDTO> postDTO2List = postDTOList.subList(0, 2);
            model.addAttribute("Post", postDTO2List);
            model.addAttribute("PostBoard", BoardName(postDTO2List));
        } else {
            model.addAttribute("Post", postDTOList);
        }


        List<MemberDTO> writerList = new ArrayList<>();
        for (PostDTO postDTO: postDTOList) {
            writerList.add(memberService.findByWriter(postDTO.getPMId()));
        }
        if(writerList.size() > 2) {
            List<MemberDTO> writer2List = writerList.subList(0, 2);
            model.addAttribute("PostWriter", writer2List);
        } else {
            model.addAttribute("PostWriter", writerList);
        }

        List<ScheduleDTO> scheduleDTOList = gatheringService.findSchedule(id);
        model.addAttribute("Schedule", scheduleDTOList);


        List<Integer> memberCount = new ArrayList<>();
        List<Long> remainingPerson = new ArrayList<>();
        for (ScheduleDTO scheduleDTO: scheduleDTOList) {
            String[] member = scheduleDTO.getSMember().split(",");
            memberCount.add(member.length);
            remainingPerson.add(scheduleDTO.getSMaxHeadcount() - member.length);
        }
        model.addAttribute("memberCount", memberCount);
        model.addAttribute("remaining", remainingPerson);

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

        List<CommentsDTO> commentsDTOList = gatheringService.findComments(pNum);
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (CommentsDTO commentsDTO: commentsDTOList) {
            memberDTOList.add(memberService.findByCommentsWriter(commentsDTO.getCMId()));
        }
        model.addAttribute("Comments", commentsDTOList);
        model.addAttribute("CommentsWriter", memberDTOList);

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

        List<ScheduleDTO> scheduleDTOList = gatheringService.findSchedule(id);
        model.addAttribute("Schedule", scheduleDTOList);


        List<Integer> memberCount = new ArrayList<>();
        List<Long> remainingPerson = new ArrayList<>();
        for (ScheduleDTO scheduleDTO: scheduleDTOList) {
            String[] member = scheduleDTO.getSMember().split(",");
            memberCount.add(member.length);
            remainingPerson.add(scheduleDTO.getSMaxHeadcount() - member.length);
        }
        model.addAttribute("memberCount", memberCount);
        model.addAttribute("remaining", remainingPerson);

        return "Gathering_Schedule";}





    @PostMapping("/create-gathering")
    public String save(@ModelAttribute GatheringDTO gatheringDTO){
        String gName = gatheringDTO.getGName();
        // MemberEntity를 검색합니다. 여기서는 검색 방법에 따라 다를 수 있습니다.
        Optional<MemberEntity> memberOptional = memberRepository.findBymId(gatheringDTO.getGMainleader());

        if (memberOptional.isPresent()) {
            // MemberEntity의 M_G_GATHERING 값을 업데이트합니다.
            MemberEntity member = memberOptional.get();

            member.setMGathering(gName);

            // MemberEntity를 저장 또는 업데이트합니다.
            memberService.save(MemberDTO.toMemberDTO(member));

            // GatheringDTO를 저장합니다.
            gatheringService.save(gatheringDTO);

            System.out.println("GatheringController.save");
            System.out.println("GatheringDTO = " + gatheringDTO);
        }
//        gatheringService.save(gatheringDTO);
//        System.out.println("GatheringController.save");
//        System.out.println("GatheringDTO = " + gatheringDTO);
        return "myClass";
    }



    @GetMapping("Gathering_Post_Write/{id}")
    public String Gathering_Post_Write(@PathVariable Long id, Model model){
        // DB 에서 모임 데이터를 가져와서 Gathering_Home에 보여준다.
        GatheringDTO gatheringDTO = gatheringService.findByGathering(id);
        model.addAttribute("GatheringHome", gatheringDTO);

        return "Gathering_Post_Write";
    }

    @PostMapping("/Gathering_Post_Write/{id}")
    public String uploadPost(@ModelAttribute PostDTO postDTO, @PathVariable Long id, HttpSession session) {
        String logInId = (String) session.getAttribute("loginId");
        postDTO.setPMId(logInId);
        postDTO.setPGNum(id);
        System.out.println(postDTO);
        System.out.println(postDTO);

        postService.uploadPost(postDTO);


        return "redirect:/Gathering_Board/" + id;
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
