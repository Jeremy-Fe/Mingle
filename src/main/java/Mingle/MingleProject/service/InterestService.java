package Mingle.MingleProject.service;
import java.util.List;

public interface InterestService {
    List<String> getAllMainSubjects();

    List<String> getSubInterestsByMainSubject(String mainSubject);
}
