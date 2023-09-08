package Mingle.MingleProject.dto;

import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.entity.PostEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class PostDTO {
    private Long pNum;
    private Long pBNum;
    private Long pGNum;
    private String pTitle;
    private String pContents;
    private Long pLike;
    private Long pViews;
    private String pMId;
    private LocalDate pDate;


//    public static Gathering gathering(GatheringDTO gatheringDTO) {
//        Gathering gathering = new Gathering();
//        gathering.setId(gatheringDTO.getId());
//        gathering.setGName(gatheringDTO.getGName());
//        return gathering;
//    }
//    public static PostDTO toPostEntity(PostDTO postDTO){
//        PostEntity postEntity = new PostEntity();
//        postEntity.setPNum(postDTO.getPNum());
//        postEntity.setPBNum(postDTO.getPBNum());
//
//    }
}
