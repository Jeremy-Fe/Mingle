package Mingle.MingleProject.dto;

import Mingle.MingleProject.entity.City;
import Mingle.MingleProject.entity.Interest;
import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.entity.ProfileImg;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class MemberDTO {
    private Long id;
    private String mId;
    private String mPwd;
    private String mName;
    private String mGender;
    private Date mBirth;
    private String mEmail;
    private City mAddress;
    private City mInteregion;
    private Interest mInterest;
    private String mGGathering;
    private ProfileImg mPiProfileimg;
    private String mIntroduction;
    private String mMbti;



    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMId(memberEntity.getMId());
        memberDTO.setMPwd(memberEntity.getMPwd());
        memberDTO.setMName(memberEntity.getMName());
        memberDTO.setMGender(memberEntity.getMGender());
        memberDTO.setMBirth(memberEntity.getMBirth());
        memberDTO.setMEmail(memberEntity.getMEmail());
        memberDTO.setMAddress(memberEntity.getMAddress());
        memberDTO.setMInteregion(memberEntity.getMInteregion());
        memberDTO.setMInterest(memberEntity.getMInterest());
        memberDTO.setMGGathering(memberEntity.getMGGathering());
        memberDTO.setMPiProfileimg(memberEntity.getMPiProfileimg());
        memberDTO.setMIntroduction(memberEntity.getMIntroduction());
        memberDTO.setMMbti(memberEntity.getMMbti());
        return memberDTO;
    }
}