package ge.RecruitCandidateAutomation.service;

import ge.RecruitCandidateAutomation.entity.Candidate;
import ge.RecruitCandidateAutomation.entity.InterviewResults;
import ge.RecruitCandidateAutomation.repository.InterviewResultsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewResultsService {

    private final InterviewResultsRepo interviewResultsRepo;

    public List<InterviewResults> findAll() {
        return interviewResultsRepo.findAll();
    }

    public InterviewResults findByCandidate(Candidate candidate) {
        return interviewResultsRepo.findByCandidate(candidate);
    }

}