package goormtask.bulletinboard.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDTO {

    private Long id;

    @Column(nullable = false)
    private String content;

    @Builder
    public CommentResponseDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
