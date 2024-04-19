package goormtask.bulletinboard.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDTO {
    private Long id;

    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(nullable = false)
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CommentResponseDTO> comments;

    @Builder
    public BoardResponseDTO(Long id, String title, String content, List<CommentResponseDTO> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }
}
