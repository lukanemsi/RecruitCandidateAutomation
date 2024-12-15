package ge.RecruitCandidateAutomation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "sender_fail_log")
@Data
public class SenderFailLog {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fail_date")
    private LocalDateTime failDate;

    @Column(name = "fail_reason", columnDefinition = "varchar(2000)")
    private String failReason;

    @ManyToOne
    private Candidate candidate;

}