package Mingle.MingleProject.service;

import Mingle.MingleProject.dto.CityDTO;
import Mingle.MingleProject.entity.CityEntity;
import Mingle.MingleProject.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

//    public List<CityDTO> getAllCities() {
//        List<CityEntity> cityEntityList = cityRepository.findAll();
////        Set<CityDTO> uniqueCityDTOs = new HashSet<>();
//        List<CityDTO> cityDTOList = new ArrayList<>();
//        for (CityEntity cityEntity : cityEntityList) {
//            cityDTOList.add(CityDTO.toCityDTO(cityEntity));
//        }
//        return new ArrayList<>(cityDTOList);
//    }

    public List<String> getDistinctBcNames() {
        List<String> bcNames = cityRepository.findAll().stream()
                .map(CityEntity::getBcName)
                .distinct()
                .collect(Collectors.toList());
        return bcNames;
    }

    public List<String> getDistinctMcNames() {
        List<String> mcNames = cityRepository.findAll().stream()
                .map(CityEntity::getMcName)
                .distinct()
                .collect(Collectors.toList());
        return mcNames;
    }
    public List<String> getDistinctScNames() {
        List<String> scNames = cityRepository.findAll()
                .stream()
                .map(CityEntity::getMcName)
                .distinct()
                .collect(Collectors.toList());
        return scNames;
    }


}


