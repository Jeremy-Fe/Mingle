package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<CityEntity, Long> {
    // 아이디로 회원 정보 조회 (select * from member_table2 where member_Id=?)
    Optional<MemberEntity> findBymId(String mId);

}
