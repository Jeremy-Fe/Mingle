package Mingle.MingleProject.service;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.repository.MemberRepository;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.nio.charset.StandardCharsets;

@Service
/*@RequiredArgsConstructor*/
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;




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
    public void introduce(String mIntroduction) {

        /*memberEntity.setMIntroduction(memberDTO.getMIntroduction());*/
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMIntroduction(mIntroduction);

        // MemberEntity 객체를 저장
        memberRepository.updateMIntroduction(mIntroduction);

    }

    //MYpage 자기 프로필 사진 업로드
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private Blob createBlobFromMultipartFile(MultipartFile multipartFile) throws IOException, SQLException {
        byte[] fileBytes = multipartFile.getBytes();
            return new SerialBlob(fileBytes);
    }
    @Transactional
    public void uploadImage(@NotNull MultipartFile mProfileimg) {
        try {
            MemberEntity memberEntity = new MemberEntity();
            Blob mProfileBlob = createBlobFromMultipartFile(mProfileimg);
            memberEntity.setMProfileimg(mProfileBlob);
            memberRepository.updatemProfileimg(mProfileBlob);

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
        if(myPageMemberEntity.isPresent()){
            MemberEntity memberEntity = myPageMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);

            return memberDTO;
        } else {
            return null;
        }
    }


    public List<MemberDTO> findByGatheringMember(String gatheringName){
        List<MemberEntity> gatheringMemberEntityList = memberRepository.findByGatheringMember(gatheringName);
        List<MemberDTO> gatheringMemberDTOList = new ArrayList<>();
        for (MemberEntity gatheringMemberEntity : gatheringMemberEntityList) {
            gatheringMemberDTOList.add(MemberDTO.toMemberDTO(gatheringMemberEntity));
        }

        return gatheringMemberDTOList;
    }

    public int findByGatheringHeadcount(String gName) {
        int headcount = memberRepository.findByGatheringHeadcount(gName);

        return headcount;
    }

}
