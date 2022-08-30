package com.kiki.board.presentation;

import com.kiki.board.application.PostsService;
import com.kiki.board.application.dto.PostsDto;
import com.kiki.board.application.dto.UserDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    /* CREATE */
    @PostMapping("/api/v1/posts")
    public ResponseEntity save(@RequestBody PostsDto.Request dto) {
        UserDto.Response user = (UserDto.Response) httpSession.getAttribute("user");

        return ResponseEntity.ok(postsService.save(dto, user.getNickname()));
    }


    /* UPDATE */
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto.Request dto) {
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* READ */
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(postsService.findById(id));
    }

    /* DELETE */
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }



}
