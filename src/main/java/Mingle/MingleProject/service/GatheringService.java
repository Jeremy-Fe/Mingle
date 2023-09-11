package Mingle.MingleProject.service;

import Mingle.MingleProject.Mapper.EntityDTOMapper;
import Mingle.MingleProject.dto.GatheringDTO;
import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.dto.PostDTO;
import Mingle.MingleProject.entity.GatheringEntity;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.entity.PostEntity;
import Mingle.MingleProject.repository.GatheringRepository;
import Mingle.MingleProject.repository.MemberRepository;
import Mingle.MingleProject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.*;


@Service
@RequiredArgsConstructor
public class GatheringService {
    private final GatheringRepository gatheringRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;


    public List<GatheringDTO> findAll() {
        // 엔티티 객체를 DTO 객체로 옮겨 담기
        List<GatheringEntity> gatheringEntityList = gatheringRepository.findAll();
        List<GatheringDTO> gatheringDTOList = new ArrayList<>();
        for (GatheringEntity gatheringEntity : gatheringEntityList) {
            gatheringDTOList.add(GatheringDTO.gatheringDTO(gatheringEntity));
        }

        return gatheringDTOList;
    }

    public GatheringDTO findByGathering(Long id) {
        Optional<GatheringEntity> optionalGathering = gatheringRepository.findById(id);
        if (optionalGathering.isPresent()) {
            GatheringEntity gatheringEntity = optionalGathering.get();
            GatheringDTO gatheringDTO = GatheringDTO.gatheringDTO(gatheringEntity);
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
public List<GatheringEntity> findMyMingles(String userId) {
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

    /*전체 0 검색 3 3개 해당*/
    public List<GatheringEntity> searchMingleCase1(String selectedRegi, String mainCtName, String subC) {
    return gatheringRepository.searchMingleCase1(selectedRegi,mainCtName,subC);
    }
    /* 전체 1 검색 2 2개 해당*/
    public List<GatheringEntity> searchMingleCase2(String selectedRegi, String mainCtName, String subC) {
        if(!selectedRegi.equals("전체")&&!mainCtName.equals("전체")&&subC.equals("전체")){
            /*지역 & 메인 != "전체" \ 세부 = "전체" */
            return gatheringRepository.searchMingleCase2_1(selectedRegi,mainCtName);
        }else if(selectedRegi.equals("전체")&&!mainCtName.equals("전체")&&!subC.equals("전체")){
            /*메인 & 세부 != "전체" \ 지역 = "전체" */
            return gatheringRepository.searchMingleCase2_2(mainCtName,subC);
        }else{
            /*세부 & 지역 != "전체" \ 메인 = "전체" */
            return gatheringRepository.searchMingleCase2_3(subC,selectedRegi);
        }
    }
    /* 전체 2 검색 1 하나만 해당*/
    public List<GatheringEntity> searchMingleCase3(String selectedRegi, String mainCtName, String subC) {
        if(!selectedRegi.equals("전체")&&mainCtName.equals("전체")&&subC.equals("전체")){
            /*지역 != "전체"*/
            System.out.println("지역만 값이 있을 때 ");
            return gatheringRepository.searchMingleCase3(selectedRegi);
        }else if(selectedRegi.equals("전체")&&!mainCtName.equals("전체")&&subC.equals("전체")){
            /*메인 카테고리 != "전체"*/
            System.out.println("메인만 값이 있을 때 ");
            return gatheringRepository.searchMingleCase3(mainCtName);
        } else {
            /*세부 카테고리 != "전체"*/
            System.out.println("세부만 값이 있을 때 ");
            return gatheringRepository.searchMingleCase3(subC);
        }
    }
    /*@카테고리 검색@*/

    /*검색창 검색*/
    public List<GatheringEntity> searchName(String searchName) {
        return gatheringRepository.searchNameCase1(searchName);
    }

    public List<GatheringEntity> searchAll() {
        return gatheringRepository.findAll();
    }
    /*@검색창 검색@*/

    public List<PostDTO> findByNotificationPost(Long id) {
        Long bNum = 5L;
        List<PostEntity> postEntityList = postRepository.findByBoardAndGathering(id, bNum);


        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity : postEntityList) {
            postDTOList.add(EntityDTOMapper.entityToDTO(postEntity));
        }

        return postDTOList;
    }

    public List<PostDTO> findByPosts(Long id) {
        Long bNum = 5L;
        List<PostEntity> postEntityList = postRepository.findByBoardAndGatheringPost(id, bNum);


        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity : postEntityList) {
            postDTOList.add(EntityDTOMapper.entityToDTO(postEntity));

        }
        // 게시글들을 가져오는 건 성공했지만 게시판 번호, 이름, 프로필 이미지 등등 가공해야할 데이터가 많음
        return postDTOList;
    }

    //    모임만들기
    public void save(GatheringDTO gatheringDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메소드 호출
        GatheringEntity gatheringEntity = GatheringEntity.gathering(gatheringDTO);
        gatheringRepository.save(gatheringEntity);
        // repository의 save메소드 호출 (조건. entity객체를 넘겨줘야 함)

    }

    // 게시글 뷰
    public PostDTO findByPost(Long pNum) {
        Optional<PostEntity> OptionalPostEntity = postRepository.findById(pNum);

        if(OptionalPostEntity.isPresent()){
            PostEntity postEntity = OptionalPostEntity.get();
            PostDTO postDTO = EntityDTOMapper.entityToDTO(postEntity);

            return postDTO;
        } else {
            return null;
        }

    }
}