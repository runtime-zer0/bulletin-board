package goormtask.bulletinboard.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDTO {

    private Long id;

    @Column(nullable = false)
    private String content;
}
