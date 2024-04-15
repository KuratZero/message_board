package id.atom.messages_board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * The data transfer object for receiving a request to create a new message.
 *
 * <p>
 * If the author is not specified, then "unknown" is indicated.
 * If the created is not specified, then now is indicated.
 * </p>
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
@Data
public class MessageDTO {
    @NotNull(message = "id is required")
    private UUID id;

    @NotNull(message = "text is required")
    @Size(min = 1, message = "text can't be empty")
    private String text;

    @NotBlank(message = "author can't be empty")
    @Size(min = 1, max = 255, message = "author's name size must be between 1 and 255'")
    private String author = "unknown";

    private Date created;
}
