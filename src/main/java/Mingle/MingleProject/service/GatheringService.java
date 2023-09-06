package Mingle.MingleProject.service;

import Mingle.MingleProject.dto.GatheringDTO;
import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GatheringService {
    private final GatheringRepository gatheringRepository;


    public List<GatheringDTO> findAll() {
        // 엔티티 객체를 DTO 객체로 옮겨 담기
        List<Gathering> gatheringEntityList = gatheringRepository.findAll();
        List<GatheringDTO> gatheringDTOList = new ArrayList<>();
        for (Gathering gatheringEntity : gatheringEntityList) {
            gatheringDTOList.add(GatheringDTO.gatheringDTO(gatheringEntity));
        }

        return gatheringDTOList;
    }

    public  GatheringDTO findByGathering(Long id){
        Optional<Gathering> optionalGathering = gatheringRepository.findById(id);
        if(optionalGathering.isPresent()){
            Gathering gathering = optionalGathering.get();
            GatheringDTO gatheringDTO = GatheringDTO.gatheringDTO(gathering);
            return gatheringDTO;
        } else {
            return null;
        }
    }
}
