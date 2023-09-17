package Mingle.MingleProject.service;

import Mingle.MingleProject.Mapper.EntityDTOMapper;
import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.dto.ScheduleDTO;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.entity.ScheduleEntity;
import Mingle.MingleProject.repository.MemberRepository;
import Mingle.MingleProject.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;
    private final ScheduleRepository scheduleRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메소드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        // repository의 save메소드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /* 1. 회원이 입력한 아이디로 DB에서 조회를 함
           2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        */
        Optional<MemberEntity> byMemberId = memberRepository.findBymId(memberDTO.getMId());
        if (byMemberId.isPresent()) {
            //조회 결가가 있다(해당 아이디를 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberId.get();
            if (memberEntity.getMPwd().equals(memberDTO.getMPwd())) {
                //비밀번호가 일치
                //entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                //비밀번호가 불일치(로그인 실패)
                return null;
            }
        } else {
            //조회 결과가 없다(해당 아이디을 가진 회원이 없다)
            return null;
        }
    }

    //회원의 모든 정보 가져오기
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    public String idCheck(String mId) {
        Optional<MemberEntity> byMId = memberRepository.findBymId(mId);
        if (byMId.isPresent()) {
            //조회결과가 있다 -> 사용할 수 없다.
        } else {
            //조회결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
        return mId;
    }

    public boolean emailExistsInDatabase(String mEmail, String mName) {
        // MemberEntity 클래스는 데이터베이스 테이블과 매핑되는 엔티티 클래스입니다.
        // findByMEmailAndMName 메서드는 JpaRepository에서 자동 생성됩니다.
        // 이 메서드를 사용하여 이메일과 mName으로 레코드를 조회합니다.
        List<MemberEntity> member = memberRepository.findMembersByEmailAndName(mEmail, mName);

        // member가 존재하면(true) 이메일과 mName으로 레코드가 일치하는 것이고,
        // member가 존재하지 않으면(false) 일치하지 않는 것입니다.
        return !member.isEmpty();
    }

    public boolean emailExistsInDatabasePw(String mEmail, String mId) {
        // MemberEntity 클래스는 데이터베이스 테이블과 매핑되는 엔티티 클래스입니다.
        // findByMEmailAndMName 메서드는 JpaRepository에서 자동 생성됩니다.
        // 이 메서드를 사용하여 이메일과 mName으로 레코드를 조회합니다.
        List<MemberEntity> member = memberRepository.findMembersByEmailAndId(mEmail, mId);

        // member가 존재하면(true) 이메일과 mName으로 레코드가 일치하는 것이고,
        // member가 존재하지 않으면(false) 일치하지 않는 것입니다.
        return !member.isEmpty();
    }


    //Mypage 자기 소개 수정
    @Transactional
    /*public void introduce(MemberDTO memberDTO)*/
    public void introduce(String mIntroduction, String mId) {

      /*  memberEntity.setMIntroduction(memberDTO.getMIntroduction());
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMIntroduction(mIntroduction);
        memberEntity.setMId(mId);*/

        // MemberEntity 객체를 저장
        memberRepository.updateMIntroduction(mIntroduction, mId);

    }

    //MYpage 자기 프로필 사진 업로드
    @Autowired
    public MemberService(MemberRepository memberRepository, ScheduleRepository scheduleRepository) {
        this.memberRepository = memberRepository;
        this.scheduleRepository = scheduleRepository;
    }

    private Blob createBlobFromMultipartFile(MultipartFile multipartFile) throws IOException, SQLException {
        byte[] fileBytes = multipartFile.getBytes();
        return new SerialBlob(fileBytes);
    }

    @Transactional
    public void uploadImage(@NotNull MultipartFile mProfileimg, String mId) {
        try {
            MemberEntity memberEntity = new MemberEntity();
            Blob mProfileBlob = createBlobFromMultipartFile(mProfileimg);
            memberEntity.setMProfileimg(mProfileBlob);
            memberRepository.updatemProfileimg(mProfileBlob, mId);

        } catch (IOException | SQLException e) {
            e.printStackTrace(); // 또는 로깅 등을 통해 예외 처리를 수행
            throw new RuntimeException("이미지 업로드 중 오류가 발생했습니다.");
        }
    }

    //Mypage 자기 프로필 사진 DB에서 부터 출력
    public String getProfileimgData(String logInId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findBymId(logInId);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity profileEntity = optionalMemberEntity.get();
            MemberDTO profileDto = MemberDTO.toMemberDTO(profileEntity);
            Blob blobTypeProfileimg = profileDto.getMProfileimg();
            System.out.println("blobTypeProfileimg :" + blobTypeProfileimg);

            if (blobTypeProfileimg != null) {
                try {
                    byte[] byteArray = blobTypeProfileimg.getBytes(1, (int) blobTypeProfileimg.length());
                    String base64Iamge = Base64.getEncoder().encodeToString(byteArray);
                    return base64Iamge;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    //MYpage 헤더로부터 로그인 된 ID 정보 유지
    public MemberDTO findbyIdMyPage(String logInId) {
        Optional<MemberEntity> myPageMemberEntity = memberRepository.findBymId(logInId);
        if (myPageMemberEntity.isPresent()) {
            MemberEntity memberEntity = myPageMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);

            return memberDTO;
        } else {
            return null;
        }
    }


    public List<MemberDTO> findByGatheringMember(String gatheringName) {
        List<MemberEntity> gatheringMemberEntityList = memberRepository.findByGatheringMember(gatheringName);
        List<MemberDTO> gatheringMemberDTOList = new ArrayList<>();
        for (MemberEntity gatheringMemberEntity : gatheringMemberEntityList) {
            gatheringMemberDTOList.add(MemberDTO.toMemberDTO(gatheringMemberEntity));
        }


        // 삭제 인덱스를 저장할 변수 초기화(향상 for문은 index가 없기때문에 직접 만들기)
        int index = 0;
        // 향상된 for 문으로 배열 순회
        for (MemberDTO memberDTO : gatheringMemberDTOList) {
            // n번째 list에 memberDTO.getMGGathering() 을 spilt으로 쪼개기
            String[] gatheringNameList = memberDTO.getMGGathering().split(",");
            // 조건문에 들어갈 변수 초기화
            boolean include = true;
            // 쪼개진 문자열을 저장한 배열을 순회하면서 모임이름이 포함되는지 확인
            for (int i = 0; i < gatheringNameList.length; i++) {
                if (gatheringNameList[i].equals(gatheringName)) {
                    include = false;
                }
            }

            // 만약 포함이 되지않아 include가 true이면 index 순서의 list null값으로 변경
            if (include) {
                gatheringMemberDTOList.set(index, null);
            }
            // 향상 for문에 index값 증가
            index++;
        }
        // 리스트중에 null값이 있다면 삭제
        while(gatheringMemberDTOList.remove(null)){}
        
        // 이렇게 해서 모임이름이 정확하지 않는 멤버 DTO를 삭제할 수 있음

        return gatheringMemberDTOList;
    }




    public boolean deleteMemberById(String mId) {
        // memberId를 사용하여 회원을 찾습니다.
        MemberEntity member = memberRepository.findBymId(mId).orElse(null);

        if (member != null) {
            // 회원을 찾았을 경우 삭제합니다.
            memberRepository.delete(member);
            return true; // 삭제 성공
        } else {
            return false; // 회원이 없음
        }
    }

    public MemberDTO findByWriter(String pMId) {
        Optional<MemberEntity> writer = memberRepository.findBymId(pMId);

        MemberDTO memberDTO = MemberDTO.toMemberDTO(writer.get());

        return memberDTO;
    }

    public MemberDTO findByCommentsWriter(String cmId) {
        Optional<MemberEntity> memberEntity = memberRepository.findBymId(cmId);

        MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity.get());
        return  memberDTO;
    }

    @Transactional
    public void updateMGathering(String mId, String gName) {
        // mId를 사용하여 회원을 찾습니다.
        Optional<MemberEntity> memberOptional = memberRepository.findBymId(mId);

        if (memberOptional.isPresent()) {
            MemberEntity member = memberOptional.get();
            String currentMGathering = member.getMGathering();

            // 현재 mGGathering 값에 새로운 gName을 추가합니다.
            if (currentMGathering != null) {
                currentMGathering += "," + gName;
            } else {
                currentMGathering = gName;
            }

            // 새로운 mGGathering 값을 설정합니다.
            member.setMGathering(currentMGathering);
            memberRepository.save(member);
        }
    }

    public List<ScheduleDTO> findByMemberId(String mId) {
        List<ScheduleEntity> scheduleEntityList = memberRepository.findByMemberId(mId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (ScheduleEntity scheduleEntity : scheduleEntityList) {
            scheduleDTOList.add(EntityDTOMapper.entityToDTO(scheduleEntity));

        }



        return scheduleDTOList;
    }

//    public boolean deleteScheduleById(String mId) {
//        // memberId를 사용하여 회원을 찾습니다.
//        List<ScheduleEntity> scheduleEntityList = memberRepository.findByMemberId(mId);
//        if (scheduleEntityList != null) {
//            // 회원을 찾았을 경우 삭제합니다.
//            for (ScheduleEntity member : scheduleEntityList) {
//                String currentMGathering = member.getSMember();
//                if (currentMGathering != null) {
//                    // MGathering에서 gName을 제거
//                    currentMGathering = currentMGathering.replace("," + mId, "").replace(mId + ",", "").replace(mId, "");
//                    member.setSMember(currentMGathering);
//                    scheduleRepository.save(member); // 업데이트된 회원 정보 저장
//                }
//
//            }
//
//            return true;
//        }
//        return false;   // 회원을 찾을 수 없거나 업데이트 실패
//    }
    @Transactional
    public void removeGathering(String currentMGathering, String mId) {
        memberRepository.removeGathering(currentMGathering, mId);
    }


}

