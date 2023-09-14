package Mingle.MingleProject.controller;

import Mingle.MingleProject.dto.ScheduleDTO;
import Mingle.MingleProject.repository.MemberRepository;
import Mingle.MingleProject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
    //생성자 주입
    private final MemberService memberService;
    private final GatheringService gatheringService;
    private final MemberRepository memberRepository;
    private final PostService postService;
    private final ScheduleService scheduleService;

    @GetMapping("Gathering_Schedule_Attend/{id}/{sNum}")
    public String scheduleAttend(@PathVariable Long id, @PathVariable Long sNum, HttpSession session){
        String logInId = (String) session.getAttribute("loginId");

        String sMember = scheduleService.attendMember(sNum);
        scheduleService.attendSchedule(logInId, sNum, sMember);


        return "redirect:/Gathering_Schedule/" + id;
    }

    @GetMapping("Gathering_Schedule_Cancel/{id}/{sNum}")
    public String scheduleCancel(@PathVariable Long id, @PathVariable Long sNum, HttpSession session){
        String logInId = (String) session.getAttribute("loginId");

        String sMember = scheduleService.attendMember(sNum);
        scheduleService.cancelSchedule(logInId, sNum, sMember);


        return "redirect:/Gathering_Schedule/" + id;
    }
}
