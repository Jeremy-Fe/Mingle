package Mingle.MingleProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "SCHEDULE")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "S_NUM", nullable = false)
    private Long sNum;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "S_G_NUM", nullable = false)
    private GatheringEntity GatheringEntity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "S_M_ID", nullable = false)
    private MemberEntity sMId;

    @Column(name = "S_TITLE", nullable = false, length = 100)
    private String sTitle;

    @Column(name = "S_DATE", nullable = false)
    private String sDate;

    @Column(name = "S_PLACE", nullable = false, length = 100)
    private String sPlace;

    @Column(name = "S_PRICE", nullable = false, length = 100)
    private String sPrice;

    @Column(name = "S_MEMBER", nullable = false, length = 1000)
    private String sMember;

    @Column(name = "S_MAXHEADCOUNT", nullable = false, length = 1000)
    private Long sMaxHeadcount;

}
