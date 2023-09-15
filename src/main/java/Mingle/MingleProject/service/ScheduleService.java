package Mingle.MingleProject.service;

import Mingle.MingleProject.Mapper.EntityDTOMapper;
import Mingle.MingleProject.dto.ScheduleDTO;
import Mingle.MingleProject.entity.ScheduleEntity;
import Mingle.MingleProject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public String attendMember(Long sNum){
        Optional<ScheduleEntity> optionalScheduleEntity = scheduleRepository.findBySNum(sNum);

        ScheduleEntity scheduleEntity = optionalScheduleEntity.get();
        ScheduleDTO scheduleDTO = EntityDTOMapper.entityToDTO(scheduleEntity);

        return scheduleDTO.getSMember();
    }

    @Transactional
    public void attendSchedule(String logInId, Long sNum, String sMember) {
        String attendId = "," + logInId;
        sMember += attendId;

        scheduleRepository.attendSchedule(sMember, sNum);
    }

    @Transactional
    public void cancelSchedule(String logInId, Long sNum, String sMember) {
        sMember = sMember.replace("," + logInId, "").replace(logInId + ",", "").replace(logInId, "");

        scheduleRepository.cancelSchedule(sNum, sMember);

    }

    public void createSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = EntityDTOMapper.DTOToEntity(scheduleDTO);
        scheduleRepository.save(scheduleEntity);
    }

    public void delete(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = EntityDTOMapper.DTOToEntity(scheduleDTO);
        scheduleRepository.save(scheduleEntity);
    }

    public void deleteSchedule(Long sNum) {
        scheduleRepository.deleteById(sNum);
    }
}
