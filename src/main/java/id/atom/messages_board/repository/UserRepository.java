package id.atom.messages_board.repository;

import id.atom.messages_board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Basic JPA repository for {@link User}
 * <p>
 * Added methods for {@link #findByLogin(String)}, and {@link #existsByLogin(String)}.
 * </p>
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);
}
