package com.sparta.kmongclonecoding.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class BigCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String bigCategory;

    @ManyToOne
    @JoinColumn
    private User user;
    //디비 관련해서 이거 유저 재고해야합니다.
}
