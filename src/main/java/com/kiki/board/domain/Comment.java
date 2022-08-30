package com.kiki.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name= "comments")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    // 게시글과 댓글은 다대일 관계
    // 하나의 게시글에 여러 개의 댓글이 있을 수 있음
    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

//     사용자와 댓글은 다대일 관계
//     하나의 사용자가 여러 개의 댓글을 달 수 있음
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    /* 댓글 수정을 위한 setter */
    public void update(String comment) {
        this.comment = comment;
    }
}
