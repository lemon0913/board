package com.kiki.board.application;

import com.kiki.board.application.dto.*;
import com.kiki.board.domain.Posts;
import com.kiki.board.infrastructure.persistence.PostsRepository;
import com.kiki.board.domain.User;
import com.kiki.board.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@RequiredArgsConstructor
@Service

public class PostsService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;



    /* CREATE */
    @Transactional
    public Long save(PostsDto.Request dto, String nickname) {

        /* User 정보를 가져와 dto에 담아준다. */
        User user = userRepository.findByNickname(nickname);
        dto.setUser(user);


        Posts posts = dto.toEntity();
        postsRepository.save(posts);

        return posts.getId();
    }

    /* UPDATE */
    @Transactional
    public void update(Long id, PostsDto.Request dto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        posts.update(dto.getTitle(), dto.getContent(), dto.getLocation(), dto.getMoney(), dto.getReservation());
    }

    /* READ */
    @Transactional(readOnly = true)
    public PostsDto.Response findById (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return new PostsDto.Response(posts);
    }

//    @Transactional(readOnly = true)
//    public List<PostsDto.Response> findAllDesc() {
//        // PostsRepository의 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메서드
//        return postsRepository.findAllDesc().stream().map(PostsDto.Response::new).collect(Collectors.toList());
//    }


    /* DELETE */
    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }


    /* Views Counting */
    @Transactional
    public int updateView(Long id) {

        return postsRepository.updateView(id);
    }

    /* Paging */
    @Transactional(readOnly = true)
    public Page<Posts> pageList(Pageable pageable) {

        return postsRepository.findAll(pageable);
    }


    /* search */
    @Transactional
    public Page<Posts> search(String title, String location, Pageable pageable) {
        Page<Posts> postsList = postsRepository.findByTitleContainingOrLocationContaining(title, location, pageable);
        return postsList;
    }


}
