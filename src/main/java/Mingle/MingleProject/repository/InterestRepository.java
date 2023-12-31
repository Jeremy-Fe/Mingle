package Mingle.MingleProject.repository;

import Mingle.MingleProject.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {
//    @Query("SELECT DISTINCT i.iMainsubject FROM Interest i")
//    List<String> findAllMainSubjects();


    @Query("SELECT DISTINCT i.iMainsubject FROM Interest i WHERE i.iMainsubject LIKE %:searchTerm% escape '/'")
    List<String> findMainSubjectsContainingText(@Param("searchTerm") String searchTerm);


    @Query("SELECT DISTINCT i.iSubject FROM Interest i WHERE i.iMainsubject = :mainSubject")
    List<String> findSubInterestsByMainSubject(@Param("mainSubject") String mainSubject);

    /*세부 괌심사 카테고리*/
    @Query("SELECT DISTINCT i.iSubject FROM Interest i WHERE i.iMainsubject =  ?1")
    List<String> findISubject(String mainSubject);


}