package com.kiki.board.infrastructure.persistence;

import com.kiki.board.domain.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts, Long> {

    // Jpa에서 기본적으로 제공하지 않는 메소드는 Query를 사용하여 작성 가능
    // 사실 Jpa에서 제공하는 기본 메소드만으로도 해결되는거지만 Query를 썼을 때 가독성이 좋으니 한번 써봄
    // Posts 리스트에 있는 모든 내용을 id 내림차순으로 조회하라!
//    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
//    List<Posts> findAllDesc();

    /** 조회수 추가 **/
    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);

    // JpaRepository에서 메소드명의 By 이후는 SQL의 where 조건 절에 대응
    // Containing을 붙여주면 Like 검색이 가능해진다. 즉, %{keyword}%가 가능하다.
    // List<Posts> findByTitleContaining(String keyword);
    // 검색 결과의 페이지네이션
    // Page<Posts> findByTitleContaining(String keyword, Pageable pageable);
    Page<Posts> findByTitleContainingOrLocationContaining(String title, String location, Pageable pageable);


}
