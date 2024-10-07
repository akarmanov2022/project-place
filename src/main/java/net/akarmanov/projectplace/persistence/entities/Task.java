package net.akarmanov.projectplace.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task extends AbstractEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @Size(max = 255)
    @NotNull
    @Column(name = "link", nullable = false)
    private String link;

    @NotNull
    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Size(max = 32)
    @NotNull
    @Column(name = "status", nullable = false, length = 32)
    private TaskStatus status;

}