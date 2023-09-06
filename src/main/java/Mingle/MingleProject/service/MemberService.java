package Mingle.MingleProject.service;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

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
            if(memberEntity.getMPwd().equals(memberDTO.getMPwd())){
                //비밀번호가 일치
                //entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }else {
                //비밀번호가 불일치(로그인 실패)
                return null;
            }
        }else {
            //조회 결과가 없다(해당 아이디을 가진 회원이 없다)
            return null;
        }
    }

    //회원의 모든 정보 가져오기
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity memberEntity : memberEntityList) {
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



    @Transactional
    /*public void introduce(MemberDTO memberDTO)*/
    public void introduce(String mIntroduction) {

            /*memberEntity.setMIntroduction(memberDTO.getMIntroduction());*/
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setMIntroduction(mIntroduction);

            // MemberEntity 객체를 저장
            memberRepository.updateMIntroduction(mIntroduction);

    }

    @Transactional
    /*public void introduce(MemberDTO memberDTO)*/
    public void proimg(String mPiProfileimg) {

        /*memberEntity.setMIntroduction(memberDTO.getMIntroduction());*/
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMPiProfileimg(mPiProfileimg);

        // MemberEntity 객체를 저장
        memberRepository.updateMPiProfileimg(mPiProfileimg);

    }
}
