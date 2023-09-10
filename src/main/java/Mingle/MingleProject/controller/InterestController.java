package Mingle.MingleProject.controller;

import Mingle.MingleProject.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interests")
public class InterestController {
    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/mainSubjects")
    public List<String> getMainSubjectsContainingText(@RequestParam("searchTerm") String searchTerm) {
        return interestService.getMainSubjectsContainingText(searchTerm);
    }

    @GetMapping("/subInterests/{mainSubject}")
    public List<String> getSubInterestsByMainSubject(@PathVariable String mainSubject) {
        List<String> subInterests = interestService.getSubInterestsByMainSubject(mainSubject);
        System.out.println("Sub Interests: " + subInterests); // 콘솔에 메시지 출력
        return subInterests;

    }



}
