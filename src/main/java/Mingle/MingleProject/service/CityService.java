package Mingle.MingleProject.service;

import Mingle.MingleProject.entity.CityEntity;
import Mingle.MingleProject.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private final CityRepository cityRepository;

    //시/군 필드조회 + 중복제거
    public List<String> getDistinctBcNames() {
        List<String> bcNames = cityRepository.findAll().stream()
                .map(CityEntity::getBcName)
                .distinct()
                .collect(Collectors.toList());
        return bcNames;
    }

    // 동적 콤보박스 만들기 (시/군 + 시/구/군 묶기)
    public Map<String, List<String>> getCityToDistrictMap() {
        List<CityEntity> cities = cityRepository.findAll();
        Map<String, List<String>> cityToDistrictMap = new HashMap<>();

        for (CityEntity city : cities) {
            String cityKey = city.getBcName();
            String districtValue = city.getMcName();

            cityToDistrictMap.computeIfAbsent(cityKey, k -> new ArrayList<>()).add(districtValue);
        }

        // Remove duplicates from the district lists
        for (List<String> districts : cityToDistrictMap.values()) {
            Set<String> uniqueDistricts = new HashSet<>(districts);
            districts.clear();
            districts.addAll(uniqueDistricts);
        }

        return cityToDistrictMap;
    }
//    지역 검색
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityEntity> searchByKeyword(String keyword) {
        return cityRepository.findByScNameContainingIgnoreCase(keyword);
    }


}


