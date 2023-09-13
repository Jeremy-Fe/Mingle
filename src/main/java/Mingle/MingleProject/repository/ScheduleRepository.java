package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

}