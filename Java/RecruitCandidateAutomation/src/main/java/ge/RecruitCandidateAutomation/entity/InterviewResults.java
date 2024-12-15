package ge.RecruitCandidateAutomation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "interview_results")
@Data
public class InterviewResults {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interview_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InterviewStatus interviewStatus;

    @Column(name = "interview_date", nullable = false)
    private LocalDateTime interviewDate;

    @Column(name = "feedback", nullable = false, columnDefinition = "varchar(2000)")
    private String feedback;

    @OneToOne(fetch = FetchType.EAGER)
    private Candidate candidate;

    public enum InterviewStatus {
        PASS,
        FAIL
    }
}
