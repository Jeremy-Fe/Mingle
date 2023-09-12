package Mingle.MingleProject.controller;

import Mingle.MingleProject.dto.RegionDTO;
import Mingle.MingleProject.entity.CityEntity;
import Mingle.MingleProject.entity.Interest;
import Mingle.MingleProject.service.CityService;
import Mingle.MingleProject.service.InterestService;
import Mingle.MingleProject.service.MemberService;
import Mingle.MingleProject.service.SubInterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegionController {
    //생성자 주입
    private final MemberService memberService;
    private final CityService cityService;
    private final SubInterestService interestService;

    /*지역 검색*/
    @GetMapping("selectRegi/regiSearch")
    public ResponseEntity<List<CityEntity>> searchCities(@RequestParam("keyword") String keyword) {
        System.out.println("keyword ck = "+keyword);
        // 검색어를 기반으로 도시 목록을 조회하는 메서드 호출
        List<CityEntity> cities = cityService.searchByKeyword(keyword);
        System.out.println("cities ck = "+cities);
        // 조회된 도시 목록을 응답(Response)에 담아 반환
        return ResponseEntity.ok(cities);
    }
    /*지역 선택*/
    @PostMapping("search2/selectedRegion")
    public String sRegiData(@RequestParam("selectedRegion") String selectedRegion, Model model) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setSelectedRegi(selectedRegion);

        System.out.println("regionDTO 시작 = "+regionDTO.getSelectedRegi());
        model.addAttribute("selectedRegion",regionDTO);

        System.out.println("regionDTO 확인 = "+regionDTO);

        return "search2";
    }
    @GetMapping("/selectInter/mS")
    public ResponseEntity<List<String>> getSubInterestsByMainSubject(String mainSubject) {
        List<String> subInterests = interestService.findSubInterestsByMainSubject(mainSubject);
        System.out.println("Sub Interests: " + subInterests);
        return ResponseEntity.ok(subInterests);
    }





}