package Mingle.MingleProject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class PostimgDTO {
    private Long piNum;
    private Long piGNum;
    private Long piBNum;
    private Long piPNum;
    private String piRoot;
}
