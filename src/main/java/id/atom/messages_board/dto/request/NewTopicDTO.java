package id.atom.messages_board.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * A data transfer object for receiving a request to create a new topic.
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
@Data
public class NewTopicDTO {
    @NotNull(message = "topic name is required")
    @NotBlank(message = "topic name cannot be empty")
    @Size(min = 1, max = 255, message = "topic name size mush be between 1 and 255 characters")
    private String topicName;

    @NotNull(message = "one message in new topic is required")
    @Valid
    private MessageDTO message;
}
