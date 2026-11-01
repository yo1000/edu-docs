package com.example.testing.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 会員（members テーブル）。JPA のエンティティ。
 */
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_rank")
    private MemberRank rank;

    /** JPA が使うためのコンストラクタ */
    protected Member() {
    }

    public Member(String name, String email, MemberRank rank) {
        this.name = name;
        this.email = email;
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public MemberRank getRank() {
        return rank;
    }

    public void changeRank(MemberRank rank) {
        this.rank = rank;
    }
}
