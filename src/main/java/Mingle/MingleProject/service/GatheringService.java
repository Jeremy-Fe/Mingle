package Mingle.MingleProject.service;

import Mingle.MingleProject.Mapper.EntityDTOMapper;
import Mingle.MingleProject.dto.GatheringDTO;
import Mingle.MingleProject.dto.PostDTO;
import Mingle.MingleProject.entity.GatheringEntity;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.entity.PostEntity;
import Mingle.MingleProject.repository.GatheringRepository;
import Mingle.MingleProject.repository.MemberRepository;
import Mingle.MingleProject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public  GatheringDTO findByGathering(Long id){
        Optional<GatheringEntity> optionalGathering = gatheringRepository.findById(id);
        if(optionalGathering.isPresent()){
            GatheringEntity gatheringEntity = optionalGathering.get();
            GatheringDTO gatheringDTO = GatheringDTO.gatheringDTO(gatheringEntity);
            return gatheringDTO;
        } else {
            return null;
        }
    }

//    public List<Gathering> findMyMingles(String userId) {
//        return gatheringRepository.findMatchingGatheringsByMemberId();
//    }
/*public List<Gathering> findMyMingles(String userId) {
    Optional<MemberEntity> member = memberRepository.findBymId(userId);  // 사용자 아이디로 MemberEntity 조회
    System.out.println("findByMId = "+userId);
    List<String> gNames = Arrays.asList(member.getMGGathering().split(",\\s*"));  // 쉼표를 기준으로 문자열을 분할하여 리스트로 변환
    System.out.println("getMGGathering = "+gNames);
    return gatheringRepository.findMatchingGatheringsByGName(gNames);
}*/
public List<GatheringEntity> findMyMingles(String userId) {
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

    public List<PostDTO> findByNotificationPost(Long id) {
        Long bNum = 5L;
        List<PostEntity> postEntityList = postRepository.findByBoardAndGathering(id, bNum);


        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity: postEntityList) {
            postDTOList.add(EntityDTOMapper.entityToDTO(postEntity));
        }

        return postDTOList;
    }

    public List<PostDTO> findByPost(Long id) {
        Long bNum = 5L;
        List<PostEntity> postEntityList = postRepository.findByBoardAndGatheringPost(id, bNum);


        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity: postEntityList) {
            postDTOList.add(EntityDTOMapper.entityToDTO(postEntity));

        }
        // 게시글들을 가져오는 건 성공했지만 게시판 번호, 이름, 프로필 이미지 등등 가공해야할 데이터가 많음
        return postDTOList;
    }
}
