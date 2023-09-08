package Mingle.MingleProject.service;

import Mingle.MingleProject.dto.GatheringDTO;
import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.repository.GatheringRepository;
import Mingle.MingleProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GatheringService {
    private final GatheringRepository gatheringRepository;
    private final MemberRepository memberRepository;


    public List<GatheringDTO> findAll() {
        // 엔티티 객체를 DTO 객체로 옮겨 담기
        List<Gathering> gatheringEntityList = gatheringRepository.findAll();
        List<GatheringDTO> gatheringDTOList = new ArrayList<>();
        for (Gathering gatheringEntity : gatheringEntityList) {
            gatheringDTOList.add(GatheringDTO.gatheringDTO(gatheringEntity));
        }

        return gatheringDTOList;
    }

    public GatheringDTO findByGathering(Long id) {
        Optional<Gathering> optionalGathering = gatheringRepository.findById(id);
        if (optionalGathering.isPresent()) {
            Gathering gathering = optionalGathering.get();
            GatheringDTO gatheringDTO = GatheringDTO.gatheringDTO(gathering);
            return gatheringDTO;
        } else {
            return null;
        }
    }

    public void save(GatheringDTO gatheringDTO){
        Gathering gathering = Gathering.gathering(gatheringDTO);
        gatheringRepository.save(gathering);
    }


}



