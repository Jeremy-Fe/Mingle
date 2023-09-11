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
@Table(name = "COMMENTS")
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_NUM", nullable = false)
    private Long cNum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "C_P_NUM", nullable = false)
    private PostEntity cPNum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "C_M_ID", nullable = false)
    private MemberEntity cMId;

    @Column(name = "C_COMMENTS", nullable = false, length = 500)
    private String cComments;

    @Column(name = "C_DATE", nullable = false)
    private LocalDate cDate;

    @OneToMany(mappedBy = "rCNum")
    private Set<ReplyEntity> replies = new LinkedHashSet<>();

}