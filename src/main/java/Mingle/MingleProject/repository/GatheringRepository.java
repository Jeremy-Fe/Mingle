package Mingle.MingleProject.repository;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GatheringRepository extends JpaRepository<Gathering, Long> {

    List<Gathering> findBygNameContainingIgnoreCase(String userId);

    @Query("SELECT g FROM Gathering g WHERE g.gName IN (SELECT m.mGGathering FROM MemberEntity m WHERE m.mId = :mId)")
    List<Gathering> findMatchingGatheringsByMemberId(@Param("mId") String memberId);

}
