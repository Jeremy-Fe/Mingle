package Mingle.MingleProject.entity;

import Mingle.MingleProject.dto.CityDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CITY")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_NUM")
    private Long cNum;

    @Column(name = "BC_NAME", nullable = false, length = 100)
    private String bcName;

    @Column(name = "MC_NAME", nullable = false, length = 100)
    private String mcName;

    @Column(name = "SC_NAME", length = 100)
    private String scName;

    public static CityEntity toCityEntity(CityDTO cityDTO) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCNum(cityDTO.getCNum());
        cityEntity.setBcName(cityDTO.getBcName());
        cityEntity.setMcName(cityDTO.getMcName());
        cityEntity.setScName(cityDTO.getScName());

        return cityEntity;
    }
}