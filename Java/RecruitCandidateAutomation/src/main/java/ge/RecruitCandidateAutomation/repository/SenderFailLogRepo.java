package ge.RecruitCandidateAutomation.repository;

import ge.RecruitCandidateAutomation.entity.Candidate;
import ge.RecruitCandidateAutomation.entity.SenderFailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenderFailLogRepo extends JpaRepository<SenderFailLog, Long> {
    List<SenderFailLog> findByCandidate(Candidate candidate);
}
