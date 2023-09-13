package Mingle.MingleProject.controller;

import Mingle.MingleProject.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 취소 기능을 처리하는 엔드포인트
    @PostMapping("/cancel-schedule/{scheduleId}")
    public String cancelSchedule(@PathVariable Long scheduleId) {
        scheduleService.cancelScheduleById(scheduleId);
        return "schedule"; // 취소 후 어떤 페이지로 리다이렉션할지 설정
    }
}