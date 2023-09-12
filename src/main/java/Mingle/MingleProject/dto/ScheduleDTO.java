package Mingle.MingleProject.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class ScheduleDTO {
    private Long sNum;
    private Long sGNum;
    private String sMId;
    private String sTitle;
    private String sDate;
    private String sPlace;
    private String sPrice;
    private String sMember;
    private Long sMaxHeadcount;

}
