package Mingle.MingleProject.dto;

import Mingle.MingleProject.entity.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Blob;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class MemberDTO {
    private String mId;
    private String mPwd;
    private String mName;
    private String mGender;
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate mBirth;
    private String mEmail;
    private String mCity;
    private String mDistrict;
    private String mIcity;
    private String mIDistrict;
    private String mInterest;
    private String mGGathering;
    private Blob mProfileimg;
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
        memberDTO.setMCity(memberEntity.getMCity());
        memberDTO.setMDistrict(memberEntity.getMDistrict());
        memberDTO.setMIcity(memberEntity.getMIcity());
        memberDTO.setMIDistrict(memberEntity.getMIDistrict());
        memberDTO.setMInterest(memberEntity.getMInterest());
        memberDTO.setMGGathering(memberEntity.getMGGathering());
        memberDTO.setMProfileimg(memberEntity.getMProfileimg());
        memberDTO.setMIntroduction(memberEntity.getMIntroduction());
        memberDTO.setMMbti(memberEntity.getMMbti());
        return memberDTO;
    }
}
