package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query("select s from ScheduleEntity s where s.sNum = :sNum")
    Optional<ScheduleEntity> findBySNum(@Param("sNum") Long sNum);


    @Modifying
    @Query("UPDATE ScheduleEntity s SET s.sMember = :attendId where s.sNum = :sNum")
    void attendSchedule(@Param("attendId")String attendId, @Param("sNum")Long sNum);

    @Modifying
    @Query("UPDATE ScheduleEntity s SET s.sMember =:sMember where s.sNum = :sNum")
    void cancelSchedule(@Param("sNum") Long sNum, @Param("sMember")String sMember);


}
