package id.atom.messages_board.repository;

import id.atom.messages_board.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Basic JPA repository for {@link Topic}
 * <p>
 * Added method with paging - {@link #findAll(Pageable)}.
 * </p>
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    Page<Topic> findAll(Pageable pageable);
}
