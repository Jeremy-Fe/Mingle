package Mingle.MingleProject.repository;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GatheringRepository extends JpaRepository<Gathering, Long> {

/*
    @Query("SELECT g FROM Gathering g WHERE g.gName IN (SELECT m.mGGathering FROM MemberEntity m WHERE m.mId = ?1)")
    List<Gathering> findMatchingGatheringsByMemberId(String userId);
*/
    @Query("SELECT g FROM Gathering g WHERE g.gName IN :gNames")
    List<Gathering> findMatchingGatheringsByGName(@Param("gNames") List<String> gNames);

/*    @Query("SELECT g FROM Gathering g WHERE m.mName = ?1 AND m.mEmail = ?2 AND = ?3")
    List<Gathering> searchMingleMingles(String selectedRegi, String mainCtName, String subC);*/
}
