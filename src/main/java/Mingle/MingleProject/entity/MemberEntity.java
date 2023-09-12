package Mingle.MingleProject.entity;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.dto.ScheduleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@DynamicInsert
@Getter
@Setter
@Entity
@Table(name = "MEMBER")
public class MemberEntity {
    @Id
    /*@GeneratedValue(strategy = GenerationType.IDENTITY) // 또는 다른 적절한 전략 선택
    private Long id; // ID 필드*/

    @Column(name = "M_ID", nullable = false, length = 50)
    private String mId;

    @Column(name = "M_PWD", nullable = false, length = 50)
    private String mPwd;

    @Column(name = "M_NAME", nullable = false, length = 50)
    private String mName;

    @Column(name = "M_GENDER", nullable = false)
    private String mGender;

    @Column(name = "M_BIRTH", nullable = false)
    private LocalDate mBirth;

    @Column(name = "M_EMAIL", nullable = false, length = 50)
    private String mEmail;

    @Column(name = "M_City", nullable = false, length = 2000)
    private String mCity;

    @Column(name = "M_District", nullable = false, length = 2000)
    private String mDistrict;

    @Column(name = "M_ICity", nullable = false)
    private String mIcity;

    @Column(name = "M_IDistrict", nullable = false)
    private String mIDistrict;

    @Column(name = "M_INTEREST", nullable = false)
    private String mInterest;

    @Column(name = "M_G_GATHERING", length = 2000)
    private String mGGathering;

    @Lob
    private Blob mProfileimg;

    @Column(name = "M_INTRODUCTION", length = 1000)
    private String mIntroduction;

    @Column(name = "M_MBTI", length = 4)
    private String mMbti;

    @OneToMany(mappedBy = "sMId", fetch = FetchType.EAGER)
    private Set<ScheduleEntity> sMId = new LinkedHashSet<>();

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMId(memberDTO.getMId());
        memberEntity.setMPwd(memberDTO.getMPwd());
        memberEntity.setMName(memberDTO.getMName());
        memberEntity.setMGender(memberDTO.getMGender());
        memberEntity.setMBirth(memberDTO.getMBirth());
        memberEntity.setMEmail(memberDTO.getMEmail());
        memberEntity.setMCity(memberDTO.getMCity());
        memberEntity.setMDistrict(memberDTO.getMDistrict());
        memberEntity.setMIcity(memberDTO.getMIcity());
        memberEntity.setMIDistrict(memberDTO.getMIDistrict());
        memberEntity.setMInterest(memberDTO.getMInterest());
        memberEntity.setMGGathering(memberDTO.getMGGathering());
        memberEntity.setMProfileimg(memberDTO.getMProfileimg());
        memberEntity.setMIntroduction(memberDTO.getMIntroduction());
        memberEntity.setMMbti(memberDTO.getMMbti());
        return memberEntity;
    }

    public void setMGathering(String mGGathering) {
        this.mGGathering = mGGathering;
    }

    public String getMGathering() {
        return this.mGGathering;
    }
}