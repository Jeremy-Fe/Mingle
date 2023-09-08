package Mingle.MingleProject.entity;

import Mingle.MingleProject.dto.GatheringDTO;
import Mingle.MingleProject.dto.InterestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "INTEREST")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_NUM")
    private Long iNum;

    @Column(name = "I_MAINSUBJECT", nullable = false, length = 100)
    private String iMainsubject;

    @Column(name = "I_SUBJECT", nullable = false, length = 100)
    private String iSubject;

    public static Interest interest(InterestDTO interestDTO) {
        Interest interest = new Interest();
        interest.setINum(interestDTO.getINum());
        interest.setIMainsubject(interestDTO.getIMainsubject());
        interest.setISubject(interestDTO.getISubject());
        return interest;
    }
}