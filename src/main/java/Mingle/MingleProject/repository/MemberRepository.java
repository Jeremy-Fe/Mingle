package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.MemberEntity;
import Mingle.MingleProject.entity.ProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 아이디로 회원 정보 조회 (select * from member_table2 where member_Id=?)
    Optional<MemberEntity> findBymId(String mId);

    //UPDATE MEMBER SET M_INTRODUCTION = 'test' WHERE M_ID = 'himedia1'
    /*@Query(value = "UPDATE MEMBER SET M_INTRODUCTION = 'test' WHERE M_ID = 'himedia1'", nativeQuery = true)*/

    @Modifying
    @Query(value = "UPDATE MemberEntity m SET m.mIntroduction  = :mIntroduction where m.mId =:mId")
    void updateMIntroduction(@Param("mIntroduction") String mIntroduction, @Param("mId") String mId);


    @Modifying
    @Query(value = "UPDATE MemberEntity m SET m.mPiProfileimg  = :mPiProfileimg where m.mId ='himedia'")
    void updateMIntroduction(@Param("mPiProfileimg") ProfileImg mPiProfileimg);
}
