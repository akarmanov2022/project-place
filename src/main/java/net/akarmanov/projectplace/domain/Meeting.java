package net.akarmanov.projectplace.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.akarmanov.projectplace.models.MeetingStatus;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "meetings")
public class Meeting {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column
    private String link;

    @Column(length = 32)
    private String number;

    @Column(nullable = false)
    private OffsetDateTime startDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column
    private byte[] screenshot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private MeetingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private TeamCard team;

    @OneToMany(mappedBy = "meeting")
    private Set<Task> meetingTasks;

}