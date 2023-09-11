package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query(value="select b from BoardEntity b where b.bNum = :noti")
    Optional<BoardEntity> findByNotification(@Param("noti") Long noti);
}
