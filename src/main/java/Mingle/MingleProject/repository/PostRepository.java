package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.BoardEntity;
import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.pGNum.id = :pGNum AND p.pBNum.bNum = :pBNum ORDER BY p.pDate DESC")
    List<PostEntity> findByNotificationPost(@Param("pGNum") Long pGNum, @Param("pBNum") Long pBNum);

}
