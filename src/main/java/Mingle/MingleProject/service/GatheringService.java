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
import java.util.*;

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


public List<Gathering> findMyMingles(String userId) {
    Optional<MemberEntity> memberOptional = memberRepository.findBymId(userId);  // 사용자 아이디로 MemberEntity 조회

    if (memberOptional.isPresent()) {  // Optional 객체가 존재하는지 확인
        MemberEntity member = memberOptional.get();
        System.out.println("findByMId = " + userId);
        List<String> gNames = Arrays.asList(member.getMGGathering().split(",\\s*"));  // 쉼표를 기준으로 문자열을 분할하여 리스트로 변환
        System.out.println("getMGahtering = " + gNames);
        return gatheringRepository.findMatchingGatheringsByGName(gNames);
    } else {
        return Collections.emptyList();  // 회원을 찾지 못한 경우 빈 리스트 반환
    }
}


 /*   public List<Gathering> searchMingleMingles(String selectedRegi, String mainCtName, String subC) {
        List<String> selectedRegi = Arrays.asList(member.getMGGathering().split(",\\s*"));
    return gatheringRepository.searchMingleMingles(selectedRegi,mainCtName,subC);
    }*/
}
