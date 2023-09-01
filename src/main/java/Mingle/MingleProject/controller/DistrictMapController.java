package Mingle.MingleProject.controller;

import Mingle.MingleProject.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DistrictMapController {

    private final CityService cityService;

    public DistrictMapController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/fetchDistrictMap")
    public ResponseEntity<Map<String, List<String>>> fetchDistrictMap() {
        Map<String, List<String>> cityToDistrictMap = cityService.getCityToDistrictMap();
        return ResponseEntity.ok(cityToDistrictMap);
    }    //회원가입 콤보박스에 필요
}
