package com.metaverse.station.back.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends PagingAndSortingRepository<Posts,Long> {

//    Page<Posts> findByUserOrderByIdDesc(User user, Pageable pageable);
    Page<Posts> findAll(Pageable pageable);

    @Override
    List<Posts> findAll();

    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);




    @Query("select l.posts from Likes l " +
            "where l.user.userSeq = :userid " +
            "AND (l.posts.categoryString like concat('%',:categoryname,'%')) ")
    Page<Posts> mylikepost(@Param("userid")Long id, @Param("categoryname") String category, Pageable pageable);

    @Query("SELECT p from Posts p WHERE p.user.userSeq = :userid AND (p.categoryString like concat('%',:categoryname,'%'))")
    Page<Posts> mypost(@Param("userid")Long id, @Param("categoryname") String category, Pageable pageable);


    Page<Posts> findPostsByCategoryStringContaining(String category,Pageable pageable);

    @Query("SELECT p from Posts p WHERE (p.content like concat('%',:keyword,'%')  or p.title like concat('%',:keyword,'%')) AND (p.categoryString like concat('%',:categoryname,'%'))")
    Page<Posts> findPostsByContentTitleCategory(@Param("keyword") String keyword, @Param("categoryname") String category  , Pageable pageable);


    //    Page<Posts> findPostsByLikesUserUserSeq(Long id,Pageable pageable);
    //    Page<Posts> findPostsByUserUserSeq(Long id, Pageable pageable);
//    Page<Posts> findPostsByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(String content, String title, Pageable pageable);

}
