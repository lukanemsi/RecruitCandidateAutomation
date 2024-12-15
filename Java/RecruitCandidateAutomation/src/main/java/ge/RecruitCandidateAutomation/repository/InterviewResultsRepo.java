package ge.RecruitCandidateAutomation.repository;

import ge.RecruitCandidateAutomation.entity.Candidate;
import ge.RecruitCandidateAutomation.entity.InterviewResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewResultsRepo extends JpaRepository<InterviewResults, Long> {
    InterviewResults findByCandidate(Candidate candidate);
}