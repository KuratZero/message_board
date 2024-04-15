package id.atom.messages_board.dto.response;

import id.atom.messages_board.domain.Topic;

import java.util.Date;
import java.util.UUID;

/**
 * A data transfer object for sending a response for a {@link Topic topic} without {@link Topic#getMessages() messages}.
 *
 * @param id      {@link UUID id} of the topic
 * @param name    name of the topic
 * @param created {@link Date timestamp} of created time of the topic
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
public record TopicDTO(UUID id, String name, Date created) {
    public TopicDTO(Topic topic) {
        this(topic.getId(), topic.getName(), topic.getCreated());
    }
}
