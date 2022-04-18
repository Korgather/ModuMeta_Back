package com.metaverse.station.back.domain.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImagesRepository extends JpaRepository<Images, Long> {

    @Query("select l.imagePath from Images l")
    List<String> getImage_pathList();

}
