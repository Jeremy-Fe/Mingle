package Mingle.MingleProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "POST")
public class PostEntity {
    @Id
    @Column(name = "P_NUM", nullable = false)
    private Long pNum;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "P_B_NUM", nullable = false)
    private BoardEntity pBNum;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "P_G_NUM", nullable = false)
    private Gathering pGNum;

    @Column(name = "P_TITLE", nullable = false, length = 100)
    private String pTitle;

    @Column(name = "P_CONTENTS", nullable = false, length = 1000)
    private String pContents;

    @Column(name = "P_LIKE")
    private Long pLike;

    @Column(name = "P_VIEWS")
    private Long pViews;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "P_M_ID", nullable = false)
    private MemberEntity pMId;

    @Column(name = "P_DATE", nullable = false)
    private LocalDate pDate;

    @OneToMany(mappedBy = "cPNum")
    private Set<CommentsEntity> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "piPNum")
    private Set<PostimgEntity> postImgs = new LinkedHashSet<>();

}