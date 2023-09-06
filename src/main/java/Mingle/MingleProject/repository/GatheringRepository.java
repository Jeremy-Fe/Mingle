package Mingle.MingleProject.repository;


import Mingle.MingleProject.entity.Gathering;
import Mingle.MingleProject.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GatheringRepository extends JpaRepository<Gathering, Long> {
//    List<Gathering> findByGMainsubjectContainingIgnoreCase(String keyword);

}