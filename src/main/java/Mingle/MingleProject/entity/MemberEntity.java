package Mingle.MingleProject.entity;

import Mingle.MingleProject.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member_table2")
public class MemberEntity {
    @Id //pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto.increment
    private Long id;

    @Column(unique = true) //unique 제약조건 추가
    private String memberId;

    @Column
    private String memberPw;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPw(memberDTO.getMemberPw());
        return memberEntity;
    }
}
