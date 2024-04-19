package goormtask.bulletinboard.controller;

import goormtask.bulletinboard.dto.BoardRequestDTO;
import goormtask.bulletinboard.dto.BoardResponseDTO;
import goormtask.bulletinboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<?> showAllPosts(){
        List<BoardResponseDTO> posts = boardService.findAllBoard();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> showPost(@PathVariable Long boardId) {
        BoardResponseDTO boardById = boardService.findBoardById(boardId);
        return new ResponseEntity<>(boardById, HttpStatus.OK);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<?> softDeletePost(@PathVariable Long boardId) {
        boardService.softDeletePost(boardId);

        return new ResponseEntity<>("Board deleted with ID: " + boardId, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBoard(@RequestBody @Validated BoardRequestDTO boardRequestDTO){
        Long boardId = boardService.createPost(boardRequestDTO);

        return new ResponseEntity<>("Board created with ID: " + boardId, HttpStatus.CREATED);

    }

    @PutMapping("/update/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDTO boardRequestDTO) {
        boardService.updatePost(boardId, boardRequestDTO);

        return new ResponseEntity<>("Board updated with ID: " + boardId, HttpStatus.OK);
    }

    @PatchMapping("/update/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable Long boardId, @RequestBody Map<String, Object> updatedFields) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
