package ge.RecruitCandidateAutomation.repository;

import ge.RecruitCandidateAutomation.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    List<Candidate> findByIsActive(boolean isActive);
}
