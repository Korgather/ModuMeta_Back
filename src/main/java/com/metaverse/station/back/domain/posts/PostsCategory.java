package com.metaverse.station.back.domain.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PostsCategory {
    METAVERSE_ZEP("METAVERSE_ZEP", "ZEP 게시판"),
    METAVERSE_GATHERTOWN("METAVERSE_GATHERTOWN", "GATHERTOWN 게시판"),
    COMMUNITY_QUESTION("COMMUNITY_QUESTION", "질문 게시판"),
    COMMUNITY_GENERAL("COMMUNITY_GENERAL", "자유 게시판"),
    COMMUNITY_STUDY("COMMUNITY_STUDY", "스터디 모집 게시판")
    ;

    private final String code;
    private final String displayName;

    public static com.metaverse.station.back.domain.posts.PostsCategory of(String code) {
        return Arrays.stream(com.metaverse.station.back.domain.posts.PostsCategory.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(COMMUNITY_GENERAL);
    }
}