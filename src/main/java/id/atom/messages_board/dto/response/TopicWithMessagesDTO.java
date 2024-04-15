package id.atom.messages_board.dto.response;

import id.atom.messages_board.domain.Message;
import id.atom.messages_board.domain.Topic;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


/**
 * A data transfer object for sending a response for a {@link Topic topic} with {@link Topic#getMessages() messages}.
 *
 * @param id       {@link UUID id} of the topic
 * @param name     name of the topic
 * @param created  {@link Date timestamp} of created time of the topic
 * @param messages list of {@link MessageDTO messageDTO}
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
public record TopicWithMessagesDTO(UUID id, String name, Date created, List<MessageDTO> messages) {
    public TopicWithMessagesDTO(Topic topic) {
        this(topic.getId(), topic.getName(), topic.getCreated(), topic.getMessages().stream().map(MessageDTO::new).toList());
    }

    public TopicWithMessagesDTO(Topic topic, Stream<Message> messageStream) {
        this(topic.getId(), topic.getName(), topic.getCreated(), messageStream.map(MessageDTO::new).toList());
    }
}
