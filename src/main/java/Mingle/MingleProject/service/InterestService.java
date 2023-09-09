package Mingle.MingleProject.service;
import java.util.List;

public interface InterestService {
    List<String> getMainSubjectsContainingText(String searchTerm);

    List<String> getSubInterestsByMainSubject(String mainSubject);

}
