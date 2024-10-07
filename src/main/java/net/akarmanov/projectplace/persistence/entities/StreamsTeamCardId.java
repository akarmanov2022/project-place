package net.akarmanov.projectplace.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StreamsTeamCardId implements Serializable {
    @Serial
    private static final long serialVersionUID = 2093936936500748046L;

    @Size(max = 32)
    @NotNull
    @Column(name = "stream_id", nullable = false, length = 32)
    private String streamId;

    @Size(max = 32)
    @NotNull
    @Column(name = "team_id", nullable = false, length = 32)
    private String teamId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StreamsTeamCardId entity = (StreamsTeamCardId) o;
        return Objects.equals(this.streamId, entity.streamId) && Objects.equals(this.teamId, entity.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamId, teamId);
    }

}