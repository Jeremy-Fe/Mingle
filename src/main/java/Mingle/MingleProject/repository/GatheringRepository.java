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

    /* @Query("SELECT g FROM Gathering g WHERE m.gDistrict = ?1 AND m.gMainsubject = ?2 AND m.gSubject= ?3")
    List<Gathering> searchMingleCase1(String selectedRegi, String mainCtName, String subC);*/

    /*    @Query("SELECT g FROM Gathering g WHERE m.gDistrict = ?1 AND m.gMainsubject = ?2  AND m.gName =?3)
    List<Gathering> searchMingleCase2(String selectedRegi, String mainCtName, String subC);*/

    /*    @Query("SELECT g FROM Gathering g WHERE m.gDistrict = ?1 AND m.gMainsubject = ?2 )
    List<Gathering> searchMingleCase3(String selectedRegi, String mainCtName, String subC);*/

}
