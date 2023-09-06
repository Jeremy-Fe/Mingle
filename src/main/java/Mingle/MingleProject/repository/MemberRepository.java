package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}
