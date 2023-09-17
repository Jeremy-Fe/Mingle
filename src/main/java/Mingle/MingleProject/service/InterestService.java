package Mingle.MingleProject.service;
import Mingle.MingleProject.entity.Interest;
import Mingle.MingleProject.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public interface InterestService {


    List<String> getMainSubjectsContainingText(String searchTerm);

    List<String> getSubInterestsByMainSubject(String mainSubject);

}

