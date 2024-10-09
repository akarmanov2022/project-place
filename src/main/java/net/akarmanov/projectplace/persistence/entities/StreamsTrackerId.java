package net.akarmanov.projectplace.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class StreamsTrackerId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1548464870812443378L;

    @NotNull
    @Column(name = "stream_id", nullable = false, length = 32)
    private UUID streamId;

    @NotNull
    @Column(name = "tracker_id", nullable = false, length = 32)
    private UUID trackerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StreamsTrackerId entity = (StreamsTrackerId) o;
        return Objects.equals(this.streamId, entity.streamId) &&
                Objects.equals(this.trackerId, entity.trackerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamId, trackerId);
    }

}