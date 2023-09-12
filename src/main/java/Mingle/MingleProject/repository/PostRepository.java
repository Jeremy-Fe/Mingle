package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.pBNum.bNum = :pBNum AND p.gatheringEntity.id = :pGNum ORDER BY p.pDate DESC")
    List<PostEntity> findByBoardAndGathering(Long pGNum, Long pBNum);

    @Query("SELECT p FROM PostEntity p WHERE p.pBNum.bNum != :pBNum AND p.gatheringEntity.id = :pGNum ORDER BY p.pDate DESC")
    List<PostEntity> findByBoardAndGatheringPost(Long pGNum, Long pBNum);


    @Query("select p from PostEntity p where p.pMId.mId =:logInId")
    List<PostEntity> findBypMId(String logInId);


}
