package id.atom.messages_board.controller;

import id.atom.messages_board.dto.request.MessageDTO;
import id.atom.messages_board.dto.request.NewTopicDTO;
import id.atom.messages_board.dto.request.TopicDTO;
import id.atom.messages_board.dto.response.TopicWithMessagesDTO;
import id.atom.messages_board.service.TopicService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @PostMapping("/topic")
    public TopicWithMessagesDTO createNewTopic(@Valid @RequestBody NewTopicDTO newTopic) {
        return topicService.createNewTopic(newTopic);
    }

    @GetMapping("/topic")
    public List<id.atom.messages_board.dto.response.TopicDTO> getTopics(@RequestParam(value = "pageNumber", defaultValue = "0") @Min(0) Integer pageNumber,
                                                                        @RequestParam(value = "pageSize", defaultValue = "10") @Min(1) @Max(50) Integer pageSize) {
        return topicService.getAllTopics(pageNumber, pageSize);
    }

    @PutMapping("/topic")
    public TopicWithMessagesDTO updateTopic(@Valid @RequestBody TopicDTO topicDTO) {
        return topicService.updateTopic(topicDTO);
    }

    @GetMapping("/topic/{topicId}")
    public TopicWithMessagesDTO getAllMessagesInTopic(@PathVariable UUID topicId,
                                                      @RequestParam(value = "pageNumber", defaultValue = "0") @Min(0) Integer pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = "10") @Min(1) @Max(50) Integer pageSize) {
        return topicService.getTopic(topicId, pageNumber, pageSize);
    }

    @PostMapping("/topic/{topicId}/message")
    public TopicWithMessagesDTO createMessageInTopic(@PathVariable UUID topicId, @Valid @RequestBody MessageDTO messageDTO) {
        return topicService.createNewMessage(topicId, messageDTO);
    }

    @PutMapping("/topic/{topicId}/message")
    public TopicWithMessagesDTO updateMessageInTopic(@PathVariable UUID topicId, @Valid @RequestBody MessageDTO messageDTO) {
        return topicService.updateMessage(topicId, messageDTO);
    }

    @DeleteMapping("/message/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable UUID messageId) {
        topicService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
