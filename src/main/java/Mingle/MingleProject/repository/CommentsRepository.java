package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

    @Query("select COUNT(c) from CommentsEntity c where c.cPNum = :pNum")
    Long findCPNumCount(@Param("pNum") Long pNum);

    @Modifying
    @Query("DELETE FROM CommentsEntity c WHERE c.cPNum = :pNum")
    void deletePostComment(Long pNum);

    @Modifying
    @Query("DELETE FROM CommentsEntity c WHERE c.cNum = :cNum")
    void deleteComment(Long cNum);

    @Query("select c from CommentsEntity c where c.cMId =:logInId order by c.cDate desc")
    List<CommentsEntity> findbycMId(String logInId);
}
