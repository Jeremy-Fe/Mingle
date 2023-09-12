package Mingle.MingleProject.service;


import Mingle.MingleProject.entity.Interest;
import Mingle.MingleProject.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubInterestService {
    @Autowired
    private final InterestRepository interestRepository;


    public SubInterestService(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }
    public List<String> findSubInterestsByMainSubject(String mainSubject) {
        return interestRepository.findISubject(mainSubject);
    }
}
