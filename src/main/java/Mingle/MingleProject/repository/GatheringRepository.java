package Mingle.MingleProject.repository;

import Mingle.MingleProject.dto.MemberDTO;
import Mingle.MingleProject.entity.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GatheringRepository extends JpaRepository<Gathering, Long> {

    List<Gathering> findBygNameContainingIgnoreCase(String userId);

}
