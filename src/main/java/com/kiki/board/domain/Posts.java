package com.kiki.board.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 500, nullable = false)
    private String location;

    @Column(length = 500, nullable = false)
    private String money;

    @Column(length = 500, nullable = false)
    private String reservation;

    // 조회수
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;


    // 네이버 Maps API를 사용하기 위한 x,y좌표
    @Column(length = 500, nullable = false)
    private String x;

    @Column(length = 500, nullable = false)
    private String y;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    // 한 게시글에 여러 댓글
    // 게시글이 삭제될 경우 댓글도 삭제되어야 함 ->  CascadeType.REMOVE
    // @OrderBy로 댓글 정렬
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comment;



    /* 게시글 수정 */
    public void update(String title, String content, String location, String money, String reservation) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.money = money;
        this.reservation = reservation;
    }

}

