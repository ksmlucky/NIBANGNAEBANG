package com.footprints.businessservice.domain.board.room.entity;

import com.footprints.businessservice.domain.board.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Article articleId;
}
