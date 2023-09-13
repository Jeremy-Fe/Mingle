package Mingle.MingleProject.dto;

import lombok.*;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String pMId;
    private LocalDate pDate;
    private Blob pImg1;
    private Blob pImg2;
    private Blob pImg3;
    private Blob pImg4;
    private Blob pImg5;


}
