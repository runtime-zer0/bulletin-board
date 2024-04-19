package goormtask.bulletinboard.service;

import goormtask.bulletinboard.dto.CommentRequestDTO;
import goormtask.bulletinboard.entity.Board;
import goormtask.bulletinboard.entity.Comment;
import goormtask.bulletinboard.repository.BoardRepository;
import goormtask.bulletinboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;


    @Transactional
    public Long createComment(Long id, CommentRequestDTO commentRequestDTO) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));
        Comment comment = Comment.builder()
                .content(commentRequestDTO.getContent())
                .board(board)
                .build();

        return  commentRepository.save(comment).getId();
    }

}
