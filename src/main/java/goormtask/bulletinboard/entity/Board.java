package goormtask.bulletinboard.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private Boolean softDeleted = false;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void assignSoftDeleted(Boolean softDeleted) {
        this.softDeleted = softDeleted;
    }

    public void assignTitle(String title) {
        this.title = title;
    }

    public void assignContent(String content) {
        this.content = content;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);

        if (comment.getBoard() != this) {
            comment.assignBoard(this);
        }
    }
}
