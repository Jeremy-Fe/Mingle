package Mingle.MingleProject.dto;

import Mingle.MingleProject.entity.MemberEntity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class MemberDTO {
    private Long id;
    private String memberId;
    private String memberPw;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberPw(memberEntity.getMemberPw());
        return memberDTO;
    }
}
