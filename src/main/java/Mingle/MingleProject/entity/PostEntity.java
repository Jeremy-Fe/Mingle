package Mingle.MingleProject.entity;

import Mingle.MingleProject.dto.PostDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
@DynamicInsert
@Getter
@Setter
@Entity
@Table(name = "POST")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_NUM", nullable = false)
    private Long pNum;

    @Column(name = "P_B_NUM", nullable = false)
    private Long pBNum;

    @Column(name = "P_G_NUM", nullable = false)
    private Long pGNum;

    @Column(name = "P_TITLE", nullable = false, length = 100)
    private String pTitle;

    @Column(name = "P_CONTENTS", nullable = false, length = 1000)
    private String pContents;

    @Column(name = "P_LIKE")
    private Long pLike;

    @Column(name = "P_M_ID", nullable = false)
    private String pMId;

    @Column(name = "P_DATE")
    private LocalDate pDate;

    @Lob
    private Blob pImg1;

    @Lob
    private Blob pImg2;

    @Lob
    private Blob pImg3;

    @Lob
    private Blob pImg4;

    @Lob
    private Blob pImg5;


    @OneToMany(mappedBy = "cPNum")
    private Set<CommentsEntity> comments = new LinkedHashSet<>();


}