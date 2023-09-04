package Mingle.MingleProject.dto;
import Mingle.MingleProject.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor  // 기본생성자 자동으로 만들어줌
@AllArgsConstructor  //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString // toString 자동으로
public class GatheringDTO {
    private long id;
    private String gName;
    private String gIntroduction;
    private long gInterest;
    private String gCity;
    private String gDistrict;
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate gDate;
    private Long gMaxheadcount;
    private String gMainleader;
    private String gSubleader1;
    private String gSubleader2;
    private String gSubleader3;
    private Long gPrivate;

    public static GatheringDTO gatheringDTO(Gathering gathering) {
        GatheringDTO gatheringDTO = new GatheringDTO();
        gatheringDTO.setId(gathering.getId());
        gatheringDTO.setGName(gathering.getGName());
        gatheringDTO.setGIntroduction(gathering.getGIntroduction());
        gatheringDTO.setGCity(gathering.getGCity());
        gatheringDTO.setGDistrict(gathering.getGDistrict());
        gatheringDTO.setGDate(gathering.getGDate());
        gatheringDTO.setGMaxheadcount(gathering.getGMaxheadcount());
        gatheringDTO.setGMainleader(gathering.getGMainleader());
        gatheringDTO.setGSubleader1(gathering.getGSubleader1());
        gatheringDTO.setGSubleader2(gathering.getGSubleader2());
        gatheringDTO.setGSubleader3(gathering.getGSubleader3());
        gatheringDTO.setGPrivate(gathering.getGPrivate());
        return gatheringDTO;
    }
}