package Mingle.MingleProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CITY")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_NUM")
    private Long cNum;

    @Column(name = "BC_NAME", nullable = false, length = 100)
    private String bcName;

    @Column(name = "MC_NAME", nullable = false, length = 100)
    private String mcName;

    @Column(name = "SC_NAME", nullable = false, length = 100)
    private String scName;

}