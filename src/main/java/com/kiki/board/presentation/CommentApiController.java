package com.kiki.board.presentation;

import com.kiki.board.application.CommentService;
import com.kiki.board.application.dto.CommentDto;

import com.kiki.board.application.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;
    private final HttpSession httpSession;

    /* CREATE */
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity save(@PathVariable Long id, @RequestBody CommentDto.Request dto) {

        UserDto.Response user = (UserDto.Response) httpSession.getAttribute("user");


        return ResponseEntity.ok(commentService.save(user.getNickname(), id, dto));

    }


    /* UPDATE */
    @PutMapping({"/posts/{id}/comments/{id}"})
    public ResponseEntity update(@PathVariable Long id, @RequestBody CommentDto.Request dto) {
        commentService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/posts/{id}/comments/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok(id);
    }

}
