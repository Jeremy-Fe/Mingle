package Mingle.MingleProject.dto;
import Mingle.MingleProject.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Blob;
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
    private String gMainsubject;
    private String gSubject;
    private String gCity;
    private String gDistrict;
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date gDate;
    private Long gMaxheadcount;
    private String gMainleader;
    private String gSubleader1;
    private String gSubleader2;
    private String gSubleader3;
    private Long gPrivate;
    private Blob gCoverimg;

    public static GatheringDTO gatheringDTO(GatheringEntity gatheringEntity) {
        GatheringDTO gatheringDTO = new GatheringDTO();
        gatheringDTO.setId(gatheringEntity.getId());
        gatheringDTO.setGName(gatheringEntity.getGName());
        gatheringDTO.setGIntroduction(gatheringEntity.getGIntroduction());
        gatheringDTO.setGMainsubject(gatheringEntity.getGMainsubject());
        gatheringDTO.setGSubject(gatheringEntity.getGSubject());
        gatheringDTO.setGCity(gatheringEntity.getGCity());
        gatheringDTO.setGDistrict(gatheringEntity.getGDistrict());
        gatheringDTO.setGDate(gatheringEntity.getGDate());
        gatheringDTO.setGMaxheadcount(gatheringEntity.getGMaxheadcount());
        gatheringDTO.setGMainleader(gatheringEntity.getGMainleader());
        gatheringDTO.setGSubleader1(gatheringEntity.getGSubleader1());
        gatheringDTO.setGSubleader2(gatheringEntity.getGSubleader2());
        gatheringDTO.setGSubleader3(gatheringEntity.getGSubleader3());
        gatheringDTO.setGPrivate(gatheringEntity.getGPrivate());
        gatheringDTO.setGCoverimg((gatheringEntity.getGCoverimg()));
        return gatheringDTO;
    }
}