package Mingle.MingleProject.repository;

import Mingle.MingleProject.dto.PostDTO;
import Mingle.MingleProject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.pBNum = :pBNum AND p.pGNum = :pGNum ORDER BY p.pDate DESC")
    List<PostEntity> findByBoardAndGathering(Long pGNum, Long pBNum);

    @Query("SELECT p FROM PostEntity p WHERE p.pBNum != :pBNum AND p.pGNum = :pGNum ORDER BY p.pDate DESC")
    List<PostEntity> findByBoardAndGatheringPost(Long pGNum, Long pBNum);


    @Query("select p from PostEntity p where p.pMId =:logInId")
    List<PostEntity> findBypMId(String logInId);

    @Modifying
    @Query("UPDATE PostEntity p SET p.pBNum = :pBNum, p.pTitle = :pTitle, p.pContents = :pContents  where p.pNum = :pNum")
    void updatePost(@Param("pNum") Long pNum, @Param("pBNum") Long pBNum, @Param("pTitle") String pTitle, @Param("pContents") String pContents);

    @Modifying
    @Query("DELETE FROM PostEntity p WHERE p.pNum = :pNum")
    void deletePost(Long pNum);
}
