package net.akarmanov.projectplace.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "streams_trackers")
public class StreamsTracker {
    @EmbeddedId
    private StreamsTrackerId id;

    @MapsId("streamId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stream_id", nullable = false)
    private Stream stream;

    @MapsId("trackerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tracker_id", nullable = false)
    private Tracker tracker;

}