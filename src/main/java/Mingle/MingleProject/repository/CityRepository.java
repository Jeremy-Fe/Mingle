package Mingle.MingleProject.repository;

import Mingle.MingleProject.dto.CityDTO;
import Mingle.MingleProject.entity.CityEntity;
import Mingle.MingleProject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

}
