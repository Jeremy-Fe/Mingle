package Mingle.MingleProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "POST_IMG")
public class PostimgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PI_ROOT", nullable = false, length = 100)
    private String piRoot;

    @Column(name = "PI_NUM", nullable = false)
    private Long piNum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PI_G_NUM", nullable = false)
    private GatheringEntity GatheringEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PI_B_NUM", nullable = false)
    private BoardEntity piBNum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PI_P_NUM", nullable = false)
    private PostEntity piPNum;


}