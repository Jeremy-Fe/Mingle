package Mingle.MingleProject.dto;

import Mingle.MingleProject.entity.CityEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class CityDTO {
    private Long cNum;
    private String bcName;
    private String mcName;
    private String scName;

    public static CityDTO toCityDTO(CityEntity cityEntity) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCNum(cityEntity.getCNum());
        cityDTO.setBcName(cityEntity.getBcName());
        cityDTO.setMcName(cityEntity.getMcName());
        cityDTO.setScName(cityEntity.getScName());

        return cityDTO;
    }
}
