package ge.RecruitCandidateAutomation.service;

import ge.RecruitCandidateAutomation.entity.Candidate;
import ge.RecruitCandidateAutomation.entity.SenderFailLog;
import ge.RecruitCandidateAutomation.repository.SenderFailLogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SenderFailLogService {
    private final SenderFailLogRepo senderFailLogRepo;

    public void save(SenderFailLog senderFailLog) {
        senderFailLogRepo.save(senderFailLog);
    }

    public List<SenderFailLog> findAll() {
        return senderFailLogRepo.findAll();
    }

    public List<SenderFailLog> findByCandidate(Candidate candidate) {
        return senderFailLogRepo.findByCandidate(candidate);
    }

    public void delete(Long id) {
        senderFailLogRepo.deleteById(id);
    }
}
