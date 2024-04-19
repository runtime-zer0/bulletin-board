package goormtask.bulletinboard.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardRequestDTO {
    private Long id;

    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(nullable = false)
    private String content;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CommentRequestDTO> comments;


}
