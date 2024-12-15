package ge.RecruitCandidateAutomation.service;

import ge.RecruitCandidateAutomation.entity.Candidate;
import ge.RecruitCandidateAutomation.repository.CandidateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepo candidateRepo;

    public List<Candidate> findAll() {
        return candidateRepo.findAll();
    }

    public List<Candidate> findByIsActive(boolean isActive) {
        return candidateRepo.findByIsActive(isActive);
    }

    public void delete(Candidate candidate) {
        candidateRepo.delete(candidate);
    }

    public void update(Candidate candidate) {
        if (candidateRepo.existsById(candidate.getId())) {
            candidateRepo.save(candidate);
        }
    }
}
