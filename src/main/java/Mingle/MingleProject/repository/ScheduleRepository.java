package Mingle.MingleProject.repository;

import org.jetbrains.annotations.Async;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Async.Schedule, Long> {
    // 쿼리 메서드 등을 추가할 수 있음
}
