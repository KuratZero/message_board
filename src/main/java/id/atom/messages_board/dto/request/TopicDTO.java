package id.atom.messages_board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * The data transfer object for receiving a request to change the {@link id.atom.messages_board.domain.Topic topic}.
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
@Data
public class TopicDTO {
    @NotNull(message = "id of topic is required")
    private UUID id;

    @NotNull(message = "name of topic is required")
    @NotBlank(message = "name of topic cannot be blank")
    @Size(min = 1, max = 255, message = "size of name of topic must be between 1 and 255")
    private String name;

    private Date created;
}
