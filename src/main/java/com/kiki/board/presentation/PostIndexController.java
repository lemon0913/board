package com.kiki.board.presentation;

import com.kiki.board.application.dto.CommentDto;
import com.kiki.board.application.dto.PostsDto;
import com.kiki.board.application.dto.UserDto;
import com.kiki.board.domain.Posts;
import com.kiki.board.application.PostsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 화면 연결 Controller
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class PostIndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")                 /* default page = 0, size = 10  */
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<Posts> list = postsService.pageList(pageable);

        UserDto.Response user = (UserDto.Response) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }

        model.addAttribute("posts", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        return "index";
    }
    /* 글 작성 */
    @GetMapping("/posts/write")
    public String write(Model model) {
        UserDto.Response user = (UserDto.Response) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "posts-write";
    }

    /* 글 상세보기 */
    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, Model model) {
        PostsDto.Response dto = postsService.findById(id);
        List<CommentDto.Response> comments = dto.getComments();


        /* 댓글 관련 */
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }


        /* 사용자 관련 */
        UserDto.Response user = (UserDto.Response) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);

            /* 게시글 작성자 본인인지 확인 */
            if (dto.getUserId().equals(user.getId())) {
                model.addAttribute("writer", true);
            }

            /* 댓글 작성자 본인인지 확인 */
            if (comments.stream().anyMatch(s -> s.getUserId().equals(user.getId()))) {
                model.addAttribute("isWriter", true);
            }
        }


        postsService.updateView(id); // views ++
        model.addAttribute("posts", dto);
        return "posts-read";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        PostsDto.Response dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        UserDto.Response user = (UserDto.Response) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }

        return "posts-update";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {

        Page<Posts> searchList = postsService.search(keyword, keyword, pageable);

        UserDto.Response user = (UserDto.Response) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("user", user);

        }

        model.addAttribute("searchList", searchList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", searchList.hasNext());
        model.addAttribute("hasPrev", searchList.hasPrevious());

        return "posts-search";
    }

//    @GetMapping("/posts/search")
//    public String search(String keyword, Model model, @PageableDefault(size = 10)
//            @SortDefault.SortDefaults({
//                    @SortDefault(sort = "view", direction = Sort.Direction.DESC),
//                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
//            })
//                    Pageable pageable) {
//
//        Page<Posts> searchList = postsService.search(keyword, keyword, pageable);
//
//        UserDto.Response user = (UserDto.Response) httpSession.getAttribute("user");
//        if (user != null) {
//            model.addAttribute("user", user);
//        }
//
//        model.addAttribute("searchList", searchList);
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
//        model.addAttribute("next", pageable.next().getPageNumber());
//        model.addAttribute("hasNext", searchList.hasNext());
//        model.addAttribute("hasPrev", searchList.hasPrevious());
//
//        return "posts-search";
//    }
}
