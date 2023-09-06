package Mingle.MingleProject.service;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.repository.CityRepository;
import Mingle.MingleProject.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GatheringService {
    private final GatheringRepository gatheringRepository;

//    public GatheringService(GatheringRepository gatheringRepository){this.gatheringRepository = gatheringRepository;}
    public List<Gathering> searchByMingle(String userId) {
        return gatheringRepository.findBygNameContainingIgnoreCase(userId);

    }


}
