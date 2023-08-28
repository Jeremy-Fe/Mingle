package Mingle.MingleProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PROFILE_IMG")
public class ProfileImg {
    @Id
    @Column(name = "PI_ROOT", nullable = false, length = 200)
    private String piRoot;

    @Column(name = "PI_NUM", nullable = false)
    private Long piNum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PI_M_ID", nullable = false)
    private MemberEntity piM;

}