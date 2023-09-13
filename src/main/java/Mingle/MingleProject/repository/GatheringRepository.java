package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.CommentsEntity;
import Mingle.MingleProject.entity.GatheringEntity;
import Mingle.MingleProject.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Blob;
import java.util.List;

public interface GatheringRepository extends JpaRepository<GatheringEntity, Long> {

/*
    @Query("SELECT g FROM Gathering g WHERE g.gName IN (SELECT m.mGGathering FROM MemberEntity m WHERE m.mId = ?1)")
    List<Gathering> findMatchingGatheringsByMemberId(String userId);
*/
    /*내모임 확인*/
    @Query("SELECT g FROM GatheringEntity g WHERE g.gName IN :gNames")
    List<GatheringEntity> findMatchingGatheringsByGName(@Param("gNames") List<String> gNames);
    @Query("SELECT g FROM GatheringEntity g WHERE g.gMainsubject IN :gInter")
    List<GatheringEntity> findMatchingMInterestByMGahtering(@Param("gInter") String gInter);

    /*카테고리 검색*/
    /*전체 검색*/
     @Query("SELECT g FROM GatheringEntity g WHERE g.gDistrict = ?1 AND g.gMainsubject = ?2 AND g.gSubject= ?3")
    List<GatheringEntity> searchMingleCase1(String selectedRegi, String mainCtName, String subC);

     /*전체 1 검색 2*/
    /* 지역 & 메인 = "전체" \ 세부 != "전체" */
    @Query("SELECT g FROM GatheringEntity g WHERE g.gDistrict = ?1 AND g.gMainsubject = ?2")
    List<GatheringEntity> searchMingleCase2_1(String selectedRegi, String mainCtName);
    /*메인 & 세부 = "전체" \ 지역 != "전체" */
    @Query("SELECT g FROM GatheringEntity g WHERE g.gMainsubject = ?1 AND g.gSubject = ?2")
    List<GatheringEntity> searchMingleCase2_2(String selectedRegi, String mainCtName);
    /*세부 & 지역 = "전체" \ 메인 != "전체" */
    @Query("SELECT g FROM GatheringEntity g WHERE g.gSubject = ?1 AND g.gDistrict = ?2")
    List<GatheringEntity> searchMingleCase2_3(String selectedRegi, String mainCtName);

    /*전체 2 검색 1 하나만 해당*/
    @Query("SELECT g FROM GatheringEntity g WHERE g.gDistrict = ?1 or g.gMainsubject = ?1 or g.gSubject= ?1")
    List<GatheringEntity> searchMingleCase3(String selectOne);


    /*검색창 검색*/
    @Query("SELECT g FROM GatheringEntity g WHERE g.gName = ?1 ")
    List<GatheringEntity> searchNameCase1(String searchName);

    @Modifying
    @Query(value = "UPDATE GatheringEntity g SET g.gCoverimg  = :gCoverimg WHERE g.id =:id")
    void updateGatheringCoverimg(@Param("gCoverimg") Blob gCoverimg, @Param("id") String id);


    @Query(value="select s from ScheduleEntity s where s.GatheringEntity.id = :id")
    List<ScheduleEntity> findBysGNum(@Param("id") Long id);

    @Query(value="select c from CommentsEntity c where c.cPNum = :pNum")
    List<CommentsEntity> findByPNum(@Param("pNum")Long pNum);

}
