package Mingle.MingleProject.service;

import Mingle.MingleProject.Mapper.EntityDTOMapper;
import Mingle.MingleProject.dto.*;
import Mingle.MingleProject.entity.*;
import Mingle.MingleProject.repository.GatheringRepository;
import Mingle.MingleProject.repository.MemberRepository;
import Mingle.MingleProject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Member;
import java.sql.Blob;
import java.sql.SQLException;
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
    /*내모임 확인*/
public List<GatheringEntity> findMyMingles(String userId) {
    Optional<MemberEntity> memberOptional = memberRepository.findBymId(userId);  // 사용자 아이디로 MemberEntity 조회

    if (memberOptional.isPresent()) {  // member가 null인지 확인
        MemberEntity member = memberOptional.get();
        System.out.println("내모임 findByMId = " + userId);
        if(member.getMGGathering() != null) {
            List<String> gNames = Arrays.asList(member.getMGGathering().split(",\\s*"));  // 쉼표를 기준으로 문자열을 분할하여 리스트로 변환
            System.out.println("내모임 getMGahtering = " + gNames);
            return gatheringRepository.findMatchingGatheringsByGName(gNames);
        }else {return Collections.emptyList();}
    } else {
        return Collections.emptyList();  // 회원을 찾지 못한 경우 빈 리스트 반환
    }
}
    /*추천모임 확인*/
    public List<GatheringEntity> findRecomMingles(String userId) {
    Optional<MemberEntity> memberOptional = memberRepository.findBymId(userId);  // 사용자 아이디로 MemberEntity 조회

    if (memberOptional.isPresent()) {  // member가 null인지 확인
        MemberEntity member = memberOptional.get();
        System.out.println("추천모임 findByMId = " + userId);
        if(member.getMInterest() != null) {
            String gInter = member.getMInterest();  // 쉼표를 기준으로 문자열을 분할하여 리스트로 변환
            System.out.println("추천모임 사용자 관심사 = " + gInter);
            return gatheringRepository.findMatchingMInterestByMGahtering(gInter);
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



    private Blob createBlobFromMultipartFile(MultipartFile multipartFile) throws IOException, SQLException {
        byte[] fileBytes = multipartFile.getBytes();
        return new SerialBlob(fileBytes);
    }
//    @Transactional
//    public void uploadImage(@NotNull MultipartFile gProfileimg, String mId) {
//        try {
//            GatheringEntity gatheringEntity = new GatheringEntity();
//            Blob mProfileBlob = createBlobFromMultipartFile(gProfileimg);
//            gatheringEntity.setGCoverimg(mProfileBlob);
//            gatheringRepository.updateGatheringCoverimg(mProfileBlob,mId);
//
//        } catch (IOException | SQLException e) {
//            e.printStackTrace(); // 또는 로깅 등을 통해 예외 처리를 수행
//            throw new RuntimeException("이미지 업로드 중 오류가 발생했습니다.");
//        }
//    }

    public List<ScheduleDTO> findSchedule(Long id) {

        List<ScheduleEntity> scheduleEntityList = gatheringRepository.findBysGNum(id);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (ScheduleEntity scheduleEntity: scheduleEntityList) {
            scheduleDTOList.add(EntityDTOMapper.entityToDTO(scheduleEntity));

        }



        return scheduleDTOList;
    }


    public List<CommentsDTO> findComments(Long pNum) {
        List<CommentsEntity> commentsEntityList = gatheringRepository.findByPNum(pNum);
        List<CommentsDTO> commentsDTOList = new ArrayList<>();
        for (CommentsEntity commentsEntity: commentsEntityList) {
            commentsDTOList.add(EntityDTOMapper.entityToDTO(commentsEntity));
        }
        return commentsDTOList;
    }


    @Transactional
    public void modifyCoverimg(String gCoverimg, long id) {
        gatheringRepository.updateCoverimg(gCoverimg, id);
    }


    @Transactional
    public void RemoveSubleader(Long id, String mId, int i) {
        if(i == 1){
            gatheringRepository.removeSubleader1(id, mId);
        } else if (i == 2){
            gatheringRepository.removeSubleader2(id, mId);
        } else if (i == 3){
            gatheringRepository.removeSubleader3(id, mId);
        }
    }

    @Transactional
    public void GrantSubleader(Long id, String mId, int i) {
        if(i == 1){
            gatheringRepository.grantSubleader1(id, mId);
        } else if (i == 2){
            gatheringRepository.grantSubleader2(id, mId);
        } else if (i == 3){
            gatheringRepository.grantSubleader3(id, mId);
        }
    }

    @Transactional
    public void forcedExit(Long id, String mId) {

    }
}