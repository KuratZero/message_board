package id.atom.messages_board.dto.response;

import id.atom.messages_board.domain.Message;

import java.util.Date;
import java.util.UUID;


/**
 * A data transfer object for sending a response for a {@link Message message}.
 *
 * @param id      {@link UUID id} of the message
 * @param text    text of the message
 * @param author  author of the message (or "unknown" if the author is not specified)
 * @param created {@link Date timestamp} of created time of the message
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
public record MessageDTO(UUID id, String text, String author, Date created) {
    public MessageDTO(Message message) {
        this(message.getId(), message.getText(), message.getAuthor(), message.getCreated());
    }
}
