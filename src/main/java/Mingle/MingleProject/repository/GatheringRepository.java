package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.GatheringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GatheringRepository extends JpaRepository<GatheringEntity, Long> {

/*
    @Query("SELECT g FROM Gathering g WHERE g.gName IN (SELECT m.mGGathering FROM MemberEntity m WHERE m.mId = ?1)")
    List<Gathering> findMatchingGatheringsByMemberId(String userId);
*/
    @Query("SELECT g FROM GatheringEntity g WHERE g.gName IN :gNames")
    List<GatheringEntity> findMatchingGatheringsByGName(@Param("gNames") List<String> gNames);

}
