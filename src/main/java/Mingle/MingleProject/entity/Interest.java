package Mingle.MingleProject.entity;

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

}