package Mingle.MingleProject.entity;

import Mingle.MingleProject.dto.GatheringDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "GATHERING")
public class GatheringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "G_NUM", nullable = false)
    private Long id;
    @Column(name = "G_NAME", nullable = false, length = 100)
    private String gName;
    @Column(name = "G_INTRODUCTION", nullable = false, length = 4000)
    private String gIntroduction;
    @Column(name = "G_MAINSUBJECT", nullable = false, length = 1000)
    private String gMainsubject;
    @Column(name = "G_SUBJECT", nullable = false, length = 1000)
    private String gSubject;
    @Column(name = "G_CITY", nullable = false, length = 1000)
    private String gCity;
    @Column(name = "G_DISTRICT", nullable = false, length = 1000)
    private String gDistrict;
    @Column(name = "G_DATE", nullable = false)
    @CreatedDate
    private Date gDate;
    @Column(name = "G_MAXHEADCOUNT", nullable = false)
    private Long   gMaxheadcount;
    @Column(name = "G_MAINLEADER", nullable = false, length = 50)
    private String gMainleader;
    @Column(name = "G_SUBLEADER_1", length = 50)
    private String gSubleader1;
    @Column(name = "G_SUBLEADER_2", length = 50)
    private String gSubleader2;
    @Column(name = "G_SUBLEADER_3", length = 50)
    private String gSubleader3;
    @Column(name = "G_PRIVATE", nullable = false)
    private Long gPrivate;
    @Column(name = "G_COVERIMG", length = 300)
    private String gCoverimg;
    @PrePersist
    protected void onCreate() {
        gDate = new Date();
    }


/*  @OneToMany(mappedBy = "GatheringEntity", fetch = FetchType.EAGER)
    private Set<PostEntity> postGNum = new LinkedHashSet<>();

    @OneToMany(mappedBy = "GatheringEntity")
    private Set<PostimgEntity> piGNum = new LinkedHashSet<>();*/

    public static GatheringEntity gathering(GatheringDTO gatheringDTO) {
        GatheringEntity gatheringEntity = new GatheringEntity();
        gatheringEntity.setId(gatheringDTO.getId());
        gatheringEntity.setGName(gatheringDTO.getGName());
        gatheringEntity.setGIntroduction(gatheringDTO.getGIntroduction());
        gatheringEntity.setGMainsubject(gatheringDTO.getGMainsubject());
        gatheringEntity.setGSubject(gatheringDTO.getGSubject());
        gatheringEntity.setGCity(gatheringDTO.getGCity());
        gatheringEntity.setGDistrict(gatheringDTO.getGDistrict());
        gatheringEntity.setGDate(gatheringDTO.getGDate());
        gatheringEntity.setGMaxheadcount(gatheringDTO.getGMaxheadcount());
        gatheringEntity.setGMainleader(gatheringDTO.getGMainleader());
        gatheringEntity.setGSubleader1(gatheringDTO.getGSubleader1());
        gatheringEntity.setGSubleader2(gatheringDTO.getGSubleader2());
        gatheringEntity.setGSubleader3(gatheringDTO.getGSubleader3());
        gatheringEntity.setGPrivate(gatheringDTO.getGPrivate());
        gatheringEntity.setGCoverimg((gatheringDTO.getGCoverimg()));
        return gatheringEntity;
    }
}