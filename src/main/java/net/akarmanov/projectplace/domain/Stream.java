package net.akarmanov.projectplace.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "streams")
public class Stream {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private Integer teamsCount;

    @ManyToMany
    @JoinTable(
            name = "streams_team_cards",
            joinColumns = @JoinColumn(name = "stream_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<TeamCard> streamsTeamCardTeamCards;

    @ManyToMany
    @JoinTable(
            name = "streams_users",
            joinColumns = @JoinColumn(name = "stream_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> streamsUserUsers;

}