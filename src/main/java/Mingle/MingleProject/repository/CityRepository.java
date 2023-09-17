package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long>{

    List<CityEntity> findBymcNameContainsIgnoreCase(String keyword);

}
