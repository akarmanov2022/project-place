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
public class StreamsTrackerId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1548464870812443378L;
    @Size(max = 32)
    @NotNull
    @Column(name = "stream_id", nullable = false, length = 32)
    private String streamId;

    @Size(max = 32)
    @NotNull
    @Column(name = "tracker_id", nullable = false, length = 32)
    private String trackerId;

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