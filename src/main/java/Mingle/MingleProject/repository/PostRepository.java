package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.BoardEntity;
import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.pBNum.bNum = :pBNum AND p.pGNum.id = :pGNum")
    List<PostEntity> findByBoardAndGathering(Long pGNum, Long pBNum);


}
