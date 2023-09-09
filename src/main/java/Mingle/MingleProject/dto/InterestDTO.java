package Mingle.MingleProject.dto;
import Mingle.MingleProject.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class InterestDTO {
    private Long iNum;
    private String iMainsubject;
    private String iSubject;

    public static InterestDTO tointerestDTO(Interest interest) {
        InterestDTO interestDTO = new InterestDTO();
        interestDTO.setINum(interest.getINum());
        interestDTO.setIMainsubject(interest.getIMainsubject());
        interestDTO.setISubject(interest.getISubject());
        return interestDTO;
    }
}