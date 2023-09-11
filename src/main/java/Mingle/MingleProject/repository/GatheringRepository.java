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

    /*카테고리 검색*/
    /*전체 검색*/
     @Query("SELECT g FROM GatheringEntity g WHERE g.gDistrict = ?1 AND g.gMainsubject = ?2 AND g.gSubject= ?3")
    List<GatheringEntity> searchMingleCase1(String selectedRegi, String mainCtName, String subC);

     /*2개 해당*/
    /*지역 & 메인 = "전체" \ 세부 != "전체" */
    @Query("SELECT g FROM GatheringEntity g WHERE g.gDistrict = ?1 AND g.gMainsubject = ?2")
    List<GatheringEntity> searchMingleCase2_1(String selectedRegi, String mainCtName);
    /*메인 & 세부 = "전체" \ 지역 != "전체" */
    @Query("SELECT g FROM GatheringEntity g WHERE g.gMainsubject = ?1 AND g.gSubject = ?2")
    List<GatheringEntity> searchMingleCase2_2(String selectedRegi, String mainCtName);
    /*세부 & 지역 = "전체" \ 메인 != "전체" */
    @Query("SELECT g FROM GatheringEntity g WHERE g.gSubject = ?1 AND g.gDistrict = ?2")
    List<GatheringEntity> searchMingleCase2_3(String selectedRegi, String mainCtName);

    /*하나만 해당*/
    @Query("SELECT g FROM GatheringEntity g WHERE g.gDistrict = ?1 or g.gMainsubject = ?1 or g.gSubject= ?1")
    List<GatheringEntity> searchMingleCase3(String selectedRegi);


    /*검색창 검색*/
    @Query("SELECT g FROM GatheringEntity g WHERE g.gName = ?1 ")
    List<GatheringEntity> searchNameCase1(String searchName);




}
