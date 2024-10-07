package net.akarmanov.projectplace.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "meetings")
public class Meeting extends AbstractEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private TeamCard team;

    @Size(max = 255)
    @Column(name = "link")
    private String link;

    @Size(max = 32)
    @Column(name = "number", length = 32)
    private String number;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "screenshot", columnDefinition = "BLOB NOT NULL")
    private byte[] screenshot;

    @Size(max = 32)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private MeetingStatus status;

}