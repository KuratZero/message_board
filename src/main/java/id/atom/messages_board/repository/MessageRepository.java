package id.atom.messages_board.repository;

import id.atom.messages_board.domain.Message;
import id.atom.messages_board.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Basic JPA repository for {@link Message}
 * <p>
 * Added method with paging - {@link #findAllByTopic(Topic, Pageable)}.
 * </p>
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
public interface MessageRepository extends JpaRepository<Message, UUID> {
    Page<Message> findAllByTopic(Topic topic, Pageable pageable);
}
