package com.example.testing.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /** メソッド名から SQL が作られる（派生クエリ） */
    Optional<Member> findByEmail(String email);

    /** メールアドレスが登録済みかを返す（派生クエリ・演習6のテスト対象） */
    boolean existsByEmail(String email);

    /** ランクで絞り込み、名前の順に並べる（派生クエリ） */
    List<Member> findByRankOrderByNameAsc(MemberRank rank);

    /** 名前に keyword を含む会員を返す（JPQL を自分で書く） */
    @Query("select m from Member m where m.name like concat('%', :keyword, '%') order by m.id")
    List<Member> searchByName(@Param("keyword") String keyword);
}
