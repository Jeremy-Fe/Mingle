package Mingle.MingleProject.service;

import Mingle.MingleProject.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestServiceImpl implements InterestService {
    private final InterestRepository interestRepository;

    @Autowired
    public InterestServiceImpl(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    @Override
    public List<String> getSubInterestsByMainSubject(String mainSubject) {
        return interestRepository.findSubInterestsByMainSubject(mainSubject);
    }

    @Override
    public List<String> getMainSubjectsContainingText(String searchTerm) {
        return interestRepository.findMainSubjectsContainingText(searchTerm);
    }
}