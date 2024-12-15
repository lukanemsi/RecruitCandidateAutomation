package ge.RecruitCandidateAutomation.scheduler;

import ge.RecruitCandidateAutomation.entity.Candidate;
import ge.RecruitCandidateAutomation.service.CandidateService;
import ge.RecruitCandidateAutomation.service.SenderFailLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class DeactivateCandidateScheduler {

    @Value("${MAIL_MAX_FAIL_COUNT}")
    private Integer MAX_FAIL_COUNT;

    private final CandidateService candidateService;

    private final SenderFailLogService senderFailLogService;

    /**
     * Deactivates Candidates if sending Email Failed more then MAX_FAIL_COUNT
     */
    @Scheduled(cron = "0 0 */5 * * *")
    public void deactivateFailedCandidates() {
        candidateService.findByIsActive(true).forEach(candidate -> {
            if (senderFailLogService.findByCandidate(candidate).size() >= MAX_FAIL_COUNT)
                setInactiveCandidate(candidate);
        });
    }

    private void setInactiveCandidate(Candidate candidate) {
        log.info("Deactivating candidate with id: " + candidate.getId());
        candidate.setActive(false);
        candidateService.update(candidate);
    }
}
