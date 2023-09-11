package Mingle.MingleProject.dto;

import Mingle.MingleProject.entity.BoardEntity;
import Mingle.MingleProject.entity.CommentsEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class CommentsDTO {
    private Long cNum;
    private Long cPNum;
    private String cMId;
    private String cComments;
    private LocalDate cDate;



}
