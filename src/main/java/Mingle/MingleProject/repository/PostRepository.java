package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.pBNum.bNum = :pBNum AND p.GatheringEntity.id = :pGNum ORDER BY p.pDate DESC")
    List<PostEntity> findByBoardAndGathering(Long pGNum, Long pBNum);

    // 여기 쿼리문 Gathering Entity 변경하고 해보자 우철아

    @Query("SELECT p FROM PostEntity p WHERE p.pBNum.bNum != :pBNum AND p.GatheringEntity.id = :pGNum ORDER BY p.pDate DESC")
    List<PostEntity> findByBoardAndGatheringPost(Long pGNum, Long pBNum);


    List<PostEntity> findByPMId(String logInId);
}
