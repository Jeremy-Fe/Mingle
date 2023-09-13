package Mingle.MingleProject.service;

import Mingle.MingleProject.repository.ScheduleRepository;
import org.jetbrains.annotations.Async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 취소 기능 구현
    public void cancelScheduleById(Long scheduleId) {
        Optional<Async.Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        if (optionalSchedule.isPresent()) {
            Async.Schedule schedule = optionalSchedule.get();

            // sMember에서 해당 ID를 지우고 참여를 하나 줄입니다.
            // 예시: schedule.removeMember(userId);
            // 예시: schedule.decreaseParticipantCount();

            scheduleRepository.save(schedule); // 변경 사항을 저장
        } else {
            // 스케줄을 찾을 수 없는 경우 예외 처리
            // 예시: throw new ScheduleNotFoundException();
        }
    }
}
