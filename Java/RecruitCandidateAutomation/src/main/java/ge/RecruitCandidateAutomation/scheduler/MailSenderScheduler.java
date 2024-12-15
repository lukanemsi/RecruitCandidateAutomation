package ge.RecruitCandidateAutomation.scheduler;

import ge.RecruitCandidateAutomation.entity.Candidate;
import ge.RecruitCandidateAutomation.entity.InterviewResults;
import ge.RecruitCandidateAutomation.entity.SenderFailLog;
import ge.RecruitCandidateAutomation.service.CandidateService;
import ge.RecruitCandidateAutomation.service.InterviewResultsService;
import ge.RecruitCandidateAutomation.service.SenderFailLogService;
import ge.RecruitCandidateAutomation.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Log4j2
@Component
@RequiredArgsConstructor
public class MailSenderScheduler {

    private final MailSenderService mailSenderService;

    private final CandidateService candidateService;

    private final InterviewResultsService interviewResultsService;

    private final SenderFailLogService senderFailLogService;

    private final Resource successEmailTemplate;

    private final Resource failEmailTemplate;

    /**
     * Sends email every hour on Monday to Active Candidates Only
     * If Successfully send then deletes it
     */
    @Scheduled(cron = "0 0 * * * *")
    private void sendMails() {
        candidateService.findByIsActive(true).forEach(
                candidate ->
                {
                    InterviewResults interviewResults = interviewResultsService.findByCandidate(candidate);
                    try {
                        log.info("Sending mail to " + candidate.getEmail());
                        mailSenderService.sendMail(
                                candidate.getEmail(),
                                subjectString(interviewResults.getInterviewStatus(), candidate.getFirstName(), candidate.getLastName()),
                                htmlBodyString(interviewResults.getInterviewStatus(), candidate.getFirstName(), candidate.getLastName(), interviewResults.getFeedback()));
                        deleteSentCandidate(candidate);
                        log.info("Mail Sent to " + candidate.getEmail());
                    } catch (Exception e) {
                        log.error("Error while sending mail", e);
                        insertSenderFailLog(candidate, e.getMessage());
                    }
                }
        );
    }

    private String subjectString(InterviewResults.InterviewStatus status, String firstName, String lastName) {
        String result = "";
        switch (status) {
            case PASS -> result = String.format("Job Offer for %s %s !!!", firstName, lastName);
            case FAIL -> result = String.format("Thanks for your effort %s %s !!!", firstName, lastName);
        }
        return result;
    }

    private String htmlBodyString(InterviewResults.InterviewStatus status, String firstName, String lastName, String feedback) throws IOException {
        Resource resource = switch (status) {
            case PASS -> successEmailTemplate;
            case FAIL -> failEmailTemplate;
        };
        return new String(Files.readAllBytes(Paths.get(resource.getURI())))
                .replace("{candidateFirstname}", firstName)
                .replace("{candidateLastname}", lastName)
                .replace("{feedback}", feedback);
    }

    private void insertSenderFailLog(Candidate candidate, String failReason) {
        log.debug("inserting sender fail for candidateId: " + candidate.getId());
        SenderFailLog senderFailLog = new SenderFailLog();
        senderFailLog.setFailDate(LocalDateTime.now());
        senderFailLog.setFailReason(failReason);
        senderFailLog.setCandidate(candidate);
        senderFailLogService.save(senderFailLog);
    }

    private void deleteSentCandidate(Candidate candidate) {
        log.info("Delete Sent Candidate: " + candidate.getEmail());
        try {
            candidateService.delete(candidate);
        } catch (Exception e) {
            log.error("Error while deleting candidate", e);
        }
    }
}
