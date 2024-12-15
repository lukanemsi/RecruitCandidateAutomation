package ge.RecruitCandidateAutomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RecruitCandidateAutomationApplication {

    // docs, readme
    public static void main(String[] args) {
        SpringApplication.run(RecruitCandidateAutomationApplication.class, args);
    }
}