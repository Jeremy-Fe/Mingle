package Mingle.MingleProject.service;

import Mingle.MingleProject.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 혹은 @Component 어노테이션을 이용하여 스프링 빈으로 등록
public class InterestServiceImpl implements InterestService {
    private final InterestRepository interestRepository; // Spring Data JPA를 이용한 Repository

    @Autowired
    public InterestServiceImpl(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    @Override
    public List<String> getAllMainSubjects() {
        // Spring Data JPA를 이용하여 모든 주요 관심사를 가져오는 코드 작성
        return interestRepository.findAllMainSubjects();
    }

    @Override
    public List<String> getSubInterestsByMainSubject(String mainSubject) {
        // Spring Data JPA를 이용하여 주요 관심사에 해당하는 세부 관심사를 가져오는 코드 작성
        return interestRepository.findSubInterestsByMainSubject(mainSubject);
    }
}