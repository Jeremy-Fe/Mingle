package Mingle.MingleProject.service;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.entity.ProfileImg;
import Mingle.MingleProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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


    @Transactional
    /*public void introduce(MemberDTO memberDTO)*/
    public void introduce(String mIntroduction, String mId) {

            /*memberEntity.setMIntroduction(memberDTO.getMIntroduction());*/
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setMIntroduction(mIntroduction);

            // MemberEntity 객체를 저장
            memberRepository.updateMIntroduction(mIntroduction, mId);

    }

    @Transactional
    /*public void introduce(MemberDTO memberDTO)*/
    public void proimg(ProfileImg mPiProfileimg) {

        /*memberEntity.setMIntroduction(memberDTO.getMIntroduction());*/
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMPiProfileimg(mPiProfileimg);

        // MemberEntity 객체를 저장
        memberRepository.updateMIntroduction(mPiProfileimg);

    }
}
