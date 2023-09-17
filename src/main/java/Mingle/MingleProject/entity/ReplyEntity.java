package Mingle.MingleProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "REPLY")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_NUM", nullable = false)
    private Long rNum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "R_C_NUM", nullable = false)
    private CommentsEntity rCNum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "R_M_ID", nullable = false)
    private MemberEntity rMId;

    @Column(name = "R_REPLY", nullable = false, length = 500)
    private String rReply;

    @Column(name = "R_DATE", nullable = false)
    private LocalDate rDate;

}