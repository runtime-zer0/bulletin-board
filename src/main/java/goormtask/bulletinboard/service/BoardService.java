package goormtask.bulletinboard.service;

import goormtask.bulletinboard.dto.BoardRequestDTO;
import goormtask.bulletinboard.dto.BoardResponseDTO;
import goormtask.bulletinboard.dto.CommentResponseDTO;
import goormtask.bulletinboard.entity.Board;
import goormtask.bulletinboard.exception.BoardNotFoundException;
import goormtask.bulletinboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponseDTO> findAllBoard() {
        Sort sort = Sort.by(Sort.Order.desc("updatedAt"));
        List<Board> boards = boardRepository.findBySoftDeletedIsFalse(sort);

        if(boards.isEmpty()){
            throw new BoardNotFoundException("There is no POST!");
        }

        return boards.stream()
                .map(post -> BoardResponseDTO.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .build())
                .collect(Collectors.toList());
    }

    public BoardResponseDTO findBoardById(Long postId) {
        Optional<Board> optionalBoard = boardRepository.findByIdAndSoftDeletedIsFalse(postId);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();

            List<CommentResponseDTO> commentResponseDTOS = board.getComments().stream()
                    .map(comment -> CommentResponseDTO.builder()
                            .id(comment.getId())
                            .content(comment.getContent())
                            .build())
                    .collect(Collectors.toList());

            return BoardResponseDTO.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .comments(commentResponseDTOS)
                    .build();
        } else {
            throw new BoardNotFoundException("Post not found with ID: " + postId);
        }
    }

    @Transactional
    public Long createPost(BoardRequestDTO board) {
        Board newBoard = Board.builder()
                .content(board.getContent())
                .title(board.getTitle())
                .build();
        return boardRepository.save(newBoard).getId();
    }

    @Transactional
    public void updatePost(Long postId, BoardRequestDTO boardRequestDTO) {
        Optional<Board> optionalBoard = boardRepository.findByIdAndSoftDeletedIsFalse(postId);

        if (optionalBoard.isPresent()) {
            Board existingBoard = optionalBoard.get();

            existingBoard.assignTitle(boardRequestDTO.getTitle());
            existingBoard.assignContent(boardRequestDTO.getContent());

            boardRepository.save(existingBoard);
        } else {
            throw new BoardNotFoundException("[Update] Post not found with ID: " + boardRequestDTO.getId());
        }
    }

    @Transactional
    public void softDeletePost(Long postId) {
        Optional<Board> optionalBoard = boardRepository.findById(postId);

        if (optionalBoard.isPresent()) {
            Board deletedBoard = optionalBoard.get();
            deletedBoard.assignSoftDeleted(true);

            boardRepository.save(deletedBoard);
        } else {
            throw new BoardNotFoundException("[Delete] Post not found with ID: " + postId);
        }
    }
}
