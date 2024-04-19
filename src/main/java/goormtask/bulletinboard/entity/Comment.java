package goormtask.bulletinboard.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    private String content;

    @Builder
    public Comment(String content, Board board){
        this.content = content;
        this.board = board;
    }

    public void assignBoard(Board board) {
        if (this.board != null) {
            this.board.getComments().remove(this);
        }
        this.board = board;

        if (!board.getComments().contains(this)) {
            board.addComment(this);
        }
    }
}
