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
public class StreamsUsersId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1548464870812443378L;

    @NotNull
    @Column(name = "stream_id", nullable = false, length = 32)
    private UUID streamId;

    @NotNull
    @Column(name = "user_id", nullable = false, length = 32)
    private UUID userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StreamsUsersId entity = (StreamsUsersId) o;
        return Objects.equals(this.streamId, entity.streamId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamId, userId);
    }

}