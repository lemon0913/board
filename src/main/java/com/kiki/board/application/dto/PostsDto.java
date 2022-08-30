package com.kiki.board.application.dto;

import com.kiki.board.domain.Posts;
import com.kiki.board.domain.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  교재와 달리 request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class PostsDto {
    /** 게시글의 등록과 수정을 처리할 요청(Request) 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;
        private String title;
        private String writer;
        private String content;
        private String location;
        private String money;
        private String reservation;
        private int view;
        private User user;
        private String x;
        private String y;

//        private LocalDateTime createdDate, modifiedDate;


        /* Dto -> Entity */
        public Posts toEntity() {
            Posts posts = Posts.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .content(content)
                    .location(location)
                    .money(money)
                    .reservation(reservation)
                    .view(0)
                    .user(user)
                    .x(x)
                    .y(y)
                    .build();

            return posts;
        }
    }

    /**
     * 게시글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String writer;
        private final String content;
        private String location;
        private String money;
        private String reservation;
        private int view;
        private List<CommentDto.Response> comments;
        private final Long userId;
        private String x;
        private String y;


        /* Entity -> Dto*/
        public Response(Posts posts) {
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.writer = posts.getWriter();
            this.content = posts.getContent();
            this.location = posts.getLocation();
            this.money = posts.getMoney();
            this.reservation = posts.getReservation();
            this.view = posts.getView();
            this.comments = posts.getComment().stream().map(CommentDto.Response::new).collect(Collectors.toList());
            this.userId = posts.getUser().getId();
            this.x = posts.getX();
            this.y = posts.getY();



        }
    }
}
