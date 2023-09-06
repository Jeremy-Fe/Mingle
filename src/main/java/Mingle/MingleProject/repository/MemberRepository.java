package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 아이디로 회원 정보 조회 (select * from member where mId=?)
    Optional<MemberEntity> findBymId(String mId);

    // 사용자 정의 JPQL 쿼리를 사용하여 mEmail과 mName이 같은 레코드를 조회
    @Query("SELECT m FROM MemberEntity m WHERE m.mEmail = ?1 AND m.mName = ?2")
    List<MemberEntity> findMembersByEmailAndName(String mEmail, String mName);

    // mName과 mEmail을 기반으로 mId를 조회하는 메서드
    @Query("SELECT m.mId FROM MemberEntity m WHERE m.mName = ?1 AND m.mEmail = ?2")
    String findMemberIdByNameAndEmail(String mName, String mEmail);

    //UPDATE MEMBER SET M_INTRODUCTION = 'test' WHERE M_ID = 'himedia1'
    /*@Query(value = "UPDATE MEMBER SET M_INTRODUCTION = 'test' WHERE M_ID = 'himedia1'", nativeQuery = true)*/

    @Modifying
    @Query(value = "UPDATE MemberEntity m SET m.mIntroduction  = :mIntroduction where m.mId =:mId")
    void updateMIntroduction(@Param("mIntroduction") String mIntroduction, @Param("mId") String mId);


    @Modifying
    @Query(value = "UPDATE MemberEntity m SET m.mPiProfileimg  = :mPiProfileimg where m.mId ='himedia'")
    void updateMIntroduction(@Param("mPiProfileimg") String mPiProfileimg);

    // 모임에 포함된 멤버를 조회하는 메서드
    @Query(value="select m FROM MemberEntity m WHERE m.mGGathering = :gatheringName")
    List<MemberEntity> findByGatheringMember(@Param("gatheringName") String gatheringName);

}
