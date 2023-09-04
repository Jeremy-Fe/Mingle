package Mingle.MingleProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "GATHERING")
public class Gathering {
    @Id
    @Column(name = "G_NUM", nullable = false)
    private Long id;
    @Column(name = "G_NAME", length = 100)
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
    private LocalDate gDate;
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

}