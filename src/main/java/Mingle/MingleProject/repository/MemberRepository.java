package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.sql.Blob;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {


    // 아이디로 회원 정보 조회 (select * from member where mId=?)
    Optional<MemberEntity> findBymId(String mId);

    @Query(value="select m from MemberEntity m where m.mId = :mId")
    List<MemberEntity> findByGatheringMId(String mId);

    // 사용자 정의 JPQL 쿼리를 사용하여 mEmail과 mName이 같은 레코드를 조회
    @Query("SELECT m FROM MemberEntity m WHERE m.mEmail = ?1 AND m.mName = ?2")
    List<MemberEntity> findMembersByEmailAndName(String mEmail, String mName);

    // mName과 mEmail을 기반으로 mId를 조회하는 메서드
    @Query("SELECT m.mId FROM MemberEntity m WHERE m.mName = ?1 AND m.mEmail = ?2")
    String findMemberIdByNameAndEmail(String mName, String mEmail);

    // 사용자 정의 JPQL 쿼리를 사용하여 mEmail과 mId 같은 레코드를 조회
    @Query("SELECT m FROM MemberEntity m WHERE m.mEmail = ?1 AND m.mId = ?2")
    List<MemberEntity> findMembersByEmailAndId(String mEmail, String mId);

    // mId와 mEmail을 기반으로 mPwd를 조회하는 메서드
    @Query("SELECT m.mPwd FROM MemberEntity m WHERE m.mId = ?1 AND m.mEmail = ?2")
    String findMemberPwdByIdAndEmail(String mId, String mEmail);

    // 모임에 포함된 멤버를 조회하는 메서드
    @Query(value="select m FROM MemberEntity m WHERE m.mGGathering like %:gatheringName%")
    List<MemberEntity> findByGatheringMember(@Param("gatheringName") String gatheringName);

    @Modifying
    @Query(value = "UPDATE MemberEntity m SET m.mIntroduction  = :mIntroduction WHERE m.mId =:mId")
    void updateMIntroduction(@Param("mIntroduction") String mIntroduction, @Param("mId") String mId);

    @Modifying
    @Query(value = "UPDATE MemberEntity m SET m.mProfileimg  = :mProfileimg WHERE m.mId =:mId")
    void updatemProfileimg(@Param("mProfileimg") Blob mProfileimg, @Param("mId") String mId);

    @Query(value="select s from ScheduleEntity s where s.sMember like %:mId%")
    List<ScheduleEntity> findByMemberId (@Param("mId") String mId);

    @Modifying
    @Query("UPDATE MemberEntity m SET m.mGGathering = :currentMGathering WHERE m.mId = :mId")
    void removeGathering(@Param("currentMGathering") String currentMGathering, @Param("mId") String mId);


    @Query("SELECT m FROM MemberEntity m WHERE m.mName = :mName")
    Optional<MemberEntity> findBymName(@Param("mName")String mName);
}
