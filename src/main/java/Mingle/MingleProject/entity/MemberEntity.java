package Mingle.MingleProject.entity;

import Mingle.MingleProject.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
public class MemberEntity {
    @Id
    @Column(name = "M_ID", nullable = false, length = 50)
    private String mId;

    @Column(name = "M_PWD", nullable = false, length = 50)
    private String mPwd;

    @Column(name = "M_NAME", nullable = false, length = 50)
    private String mName;

    @Column(name = "M_GENDER", nullable = false)
    private String mGender;

    @Column(name = "M_BIRTH", nullable = false)
    private Date mBirth;

    @Column(name = "M_EMAIL", nullable = false, length = 50)
    private String mEmail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "M_ADDRESS", nullable = false)
    private City mAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "M_INTEREGION", nullable = false)
    private City mInteregion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "M_INTEREST", nullable = false)
    private Interest mInterest;

    @Column(name = "M_G_GATHERING", length = 2000)
    private String mGGathering;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "M_PI_PROFILEIMG")
    private ProfileImg mPiProfileimg;

    @Column(name = "M_INTRODUCTION", length = 1000)
    private String mIntroduction;

    @Column(name = "M_MBTI", length = 4)
    private String mMbti;

    @OneToMany(mappedBy = "piM")
    private Set<ProfileImg> profileImgs = new LinkedHashSet<>();

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMId(memberDTO.getMId());
        memberEntity.setMPwd(memberDTO.getMPwd());
        memberEntity.setMName(memberDTO.getMName());
        memberEntity.setMGender(memberDTO.getMGender());
        memberEntity.setMBirth(memberDTO.getMBirth());
        memberEntity.setMEmail(memberDTO.getMEmail());
        memberEntity.setMAddress(memberDTO.getMAddress());
        memberEntity.setMInteregion(memberDTO.getMInteregion());
        memberEntity.setMInterest(memberDTO.getMInterest());
        memberEntity.setMGGathering(memberDTO.getMGGathering());
        memberEntity.setMPiProfileimg(memberDTO.getMPiProfileimg());
        memberEntity.setMIntroduction(memberDTO.getMIntroduction());
        memberEntity.setMMbti(memberEntity.getMMbti());
        return memberEntity;
    }

}