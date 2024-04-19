package goormtask.bulletinboard.controller;

import goormtask.bulletinboard.dto.BoardRequestDTO;
import goormtask.bulletinboard.dto.CommentRequestDTO;
import goormtask.bulletinboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PatchMapping("/{boardId}")
    public ResponseEntity<?> softDeleteComment(@PathVariable Long boardId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/{boardId}")
    public ResponseEntity<?> createComment(@PathVariable Long boardId, @RequestBody @Validated CommentRequestDTO commentRequestDTO) {

        Long commentId = commentService.createComment(boardId, commentRequestDTO);

        return new ResponseEntity<>("Create Comment - boardId : " + boardId + " commentId: " + commentId, HttpStatus.CREATED);
    }

    @PutMapping("/update/{boardId}")
    public ResponseEntity<?> updateComment(@PathVariable Long boardId, @RequestBody BoardRequestDTO boardRequestDTO) {

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping("/update/{boardId}")
    public ResponseEntity<?> updateComment(@PathVariable Long boardId, @RequestBody Map<String, Object> updatedFields) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
