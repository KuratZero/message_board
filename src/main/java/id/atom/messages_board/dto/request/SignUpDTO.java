package id.atom.messages_board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Credential for receiving a request to register a new user.
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
@Data
public class SignUpDTO {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 100)
    private String login;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 255)
    private String password;
}
