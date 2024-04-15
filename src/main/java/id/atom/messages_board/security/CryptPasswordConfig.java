package id.atom.messages_board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Configuration with bean for BCryptPasswordEncoder.
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
@Configuration
public class CryptPasswordConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
