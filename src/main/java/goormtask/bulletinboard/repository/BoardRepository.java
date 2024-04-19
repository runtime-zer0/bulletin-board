package goormtask.bulletinboard.repository;

import goormtask.bulletinboard.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findBySoftDeletedIsFalse(Sort sort);

    Optional<Board> findByIdAndSoftDeletedIsFalse(Long id);


}
