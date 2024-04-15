package id.atom.messages_board.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Message {
    @Id
    private UUID id;

    private String text;

    private String author;

    @CreationTimestamp
    private Date created;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Topic topic;
}
