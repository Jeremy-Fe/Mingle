package Mingle.MingleProject.service;

import Mingle.MingleProject.entity.CityEntity;
import Mingle.MingleProject.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<String> getDistinctBcNames() {
        List<String> bcNames = cityRepository.findAll().stream()
                .map(CityEntity::getBcName)
                .distinct()
                .collect(Collectors.toList());
        return bcNames;
    }

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

}


