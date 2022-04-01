package com.metaverse.station.back.domain.posts;

import com.metaverse.station.back.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostsRepository extends PagingAndSortingRepository<Posts,Long> {

//    Page<Posts> findByUserOrderByIdDesc(User user, Pageable pageable);
//    Page<Posts> findAll(Pageable pageable);

}
