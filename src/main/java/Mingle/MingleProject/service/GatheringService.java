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

/* 메인페이지_내모임*/
/*public List<Gathering> findMyMingles(String userId) {
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
}*/
public List<Gathering> findMyMingles(String userId) {
    Optional<MemberEntity> memberOptional = memberRepository.findBymId(userId);  // 사용자 아이디로 MemberEntity 조회

    if (memberOptional.isPresent()) {  // member가 null인지 확인
        MemberEntity member = memberOptional.get();
        System.out.println("findByMId = " + userId);
        if(member.getMGGathering() != null) {
            List<String> gNames = Arrays.asList(member.getMGGathering().split(",\\s*"));  // 쉼표를 기준으로 문자열을 분할하여 리스트로 변환
            System.out.println("getMGahtering = " + gNames);
            return gatheringRepository.findMatchingGatheringsByGName(gNames);
        }else {return Collections.emptyList();}
    } else {
        return Collections.emptyList();  // 회원을 찾지 못한 경우 빈 리스트 반환
    }
}

    /*serach*/

    /*카테고리 검색*/
    /*전체 검색*/

    /*3개 해당*/
    public List<Gathering> searchMingleCase1(String selectedRegi, String mainCtName, String subC) {
    return gatheringRepository.searchMingleCase1(selectedRegi,mainCtName,subC);
    }
    /*2개 해당*/
    public List<Gathering> searchMingleCase2(String selectedRegi, String mainCtName, String subC) {
        if(!subC.equals("전체")){
            /*지역 & 메인 = "전체" \ 세부 != "전체" */
            return gatheringRepository.searchMingleCase2_1(selectedRegi,mainCtName);
        }else if(!selectedRegi.equals("전체")){
            /*메인 & 세부 = "전체" \ 지역 != "전체" */
            return gatheringRepository.searchMingleCase2_2(mainCtName,subC);
        }else{
            /*세부 & 지역 = "전체" \ 메인 != "전체" */
            return gatheringRepository.searchMingleCase2_3(subC,selectedRegi);
        }
    }
    /*하나만 해당*/
    public List<Gathering> searchMingleCase3(String selectedRegi, String mainCtName, String subC) {
        if(selectedRegi.equals("전체")){
            /*지역 = "전체"*/
            return gatheringRepository.searchMingleCase3(selectedRegi);
        }else if(mainCtName.equals("전체")){
            /*메인 카테고리 = "전체"*/
            return gatheringRepository.searchMingleCase3(mainCtName);
        } else {
            /*세부 카테고리 = "전체"*/
            return gatheringRepository.searchMingleCase3(subC);
        }
    }
    /*@카테고리 검색@*/

    /*검색창 검색*/
    public List<Gathering> searchName(String searchName) {
    return gatheringRepository.searchNameCase1(searchName);
    }
    public List<Gathering> searchAll() {
        return gatheringRepository.findAll();
    }
    /*@검색창 검색@*/

  /*  public List<PostDTO> findByNotificationPost(Long id) {
        공지사항 가져올 거다 우철아 디비버에 쿼리문 적어놨으니까 여기서부터 빡 집중해라 시발아
    }*/
}
