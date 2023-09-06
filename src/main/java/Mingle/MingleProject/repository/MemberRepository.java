package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 아이디로 회원 정보 조회 (select * from member where mId=?)
    Optional<MemberEntity> findBymId(String mId);




}
