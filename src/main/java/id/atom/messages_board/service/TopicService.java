package id.atom.messages_board.service;

import id.atom.messages_board.dto.request.MessageDTO;
import id.atom.messages_board.dto.request.NewTopicDTO;
import id.atom.messages_board.dto.request.TopicDTO;
import id.atom.messages_board.domain.Message;
import id.atom.messages_board.domain.Topic;
import id.atom.messages_board.dto.response.TopicWithMessagesDTO;
import id.atom.messages_board.exception.NoSuchData;
import id.atom.messages_board.repository.MessageRepository;
import id.atom.messages_board.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Just topic service.
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final MessageRepository messageRepository;

    public Topic findTopic(UUID id) {
        return topicRepository.findById(id).orElseThrow(() -> new NoSuchData("topic"));
    }

    public Message findMessage(UUID id) {
        return messageRepository.findById(id).orElseThrow(() -> new NoSuchData("message"));
    }


    public Message createMessageFromCred(Message message, Topic topic, MessageDTO messageDTO) {
        message.setId(messageDTO.getId());
        message.setText(messageDTO.getText());
        message.setAuthor(messageDTO.getAuthor());
        if (messageDTO.getCreated() != null) {
            message.setCreated(messageDTO.getCreated());
        }

        if (topic != null)
            message.setTopic(topic);

        return message;
    }

    public TopicWithMessagesDTO createNewTopic(NewTopicDTO newTopic) {
        Topic topic = new Topic();

        topic.setName(newTopic.getTopicName());
        topic = topicRepository.save(topic);

        Message message = createMessageFromCred(new Message(), topic, newTopic.getMessage());
        topic.setMessages(List.of(messageRepository.save(message)));

        return new TopicWithMessagesDTO(topic);
    }

    public List<id.atom.messages_board.dto.response.TopicDTO> getAllTopics(int pageNumber, int pageSize) {
        return topicRepository.findAll(PageRequest.of(pageNumber, pageSize)).stream().map(id.atom.messages_board.dto.response.TopicDTO::new).toList();
    }

    public TopicWithMessagesDTO updateTopic(TopicDTO topicDTO) {
        Topic topic = findTopic(topicDTO.getId());

        topic.setCreated(topicDTO.getCreated());
        topic.setName(topicDTO.getName());

        return new TopicWithMessagesDTO(topicRepository.save(topic));
    }

    public TopicWithMessagesDTO getTopic(UUID topicId, int pageNumber, int pageSize) {
        Topic topic = findTopic(topicId);
        return new TopicWithMessagesDTO(topic, messageRepository.findAllByTopic(topic, PageRequest.of(pageNumber, pageSize)).get());
    }

    public TopicWithMessagesDTO createNewMessage(UUID topicId, MessageDTO messageDTO) {
        Topic topic = findTopic(topicId);

        topic.getMessages().add(createMessageFromCred(new Message(), topic, messageDTO));

        return new TopicWithMessagesDTO(topicRepository.save(topic));
    }

    public TopicWithMessagesDTO updateMessage(UUID topicId, MessageDTO messageDTO) {
        Message message = findMessage(messageDTO.getId());

        if (!message.getTopic().getId().equals(topicId)) {
            throw new NoSuchData(String.format("message in topic %s", topicId));
        }

        createMessageFromCred(message, null, messageDTO);

        messageRepository.save(message);

        return new TopicWithMessagesDTO(findTopic(topicId));
    }

    public void deleteMessage(UUID id) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new NoSuchData("message"));

        if (message.getTopic().getMessages().size() == 1) {
            topicRepository.delete(message.getTopic());
        }

        messageRepository.delete(message);
    }
}
