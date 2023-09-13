package Mingle.MingleProject.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "COMMENTS")
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_NUM", nullable = false)
    private Long cNum;

    @Column(name = "C_P_NUM", nullable = false)
    private Long cPNum;

    @Column(name = "C_M_ID", nullable = false)
    private String cMId;

    @Column(name = "C_COMMENTS", nullable = false, length = 500)
    private String cComments;

    @ColumnDefault("SYSDATE")
    @Column(name = "C_DATE")
    private LocalDate cDate;


}