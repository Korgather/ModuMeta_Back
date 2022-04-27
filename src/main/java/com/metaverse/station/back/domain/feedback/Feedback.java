package com.metaverse.station.back.domain.feedback;

import com.metaverse.station.back.domain.BaseTimeEntity;
import com.metaverse.station.back.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Feedback extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public Feedback(Long id, String content, User user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

}
