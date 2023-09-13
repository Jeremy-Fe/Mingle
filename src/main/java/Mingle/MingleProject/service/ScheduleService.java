package Mingle.MingleProject.service;

import Mingle.MingleProject.entity.ScheduleEntity;
import Mingle.MingleProject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleEntity findBymId(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public void saveSchedule(ScheduleEntity schedule) {
        scheduleRepository.save(schedule);
    }

    // 다른 서비스 메서드들 추가 가능
}