package com.example.testing.member;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.dao.DataIntegrityViolationException;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * 第3部：JPA のテスト（@DataJpaTest + Testcontainers）
 * <p>
 * テストデータは TestEntityManager で登録し、テスト対象のリポジトリ（save など）では登録しない。
 * 登録したら flush でデータベースへ送り、clear で JPA の記憶を消してから検索する。
 */
@DataJpaTest
@Testcontainers
@DisplayName("MemberRepository：JPA")
class MemberRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17-alpine");

    @Autowired
    private MemberRepository memberRepository;

    /** テストデータの登録用 */
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("メールアドレスで会員を取得できる")
    void findByEmail() {
        // Arrange：テストデータを登録し、SQL を送ってから、JPA の記憶を消す
        entityManager.persist(new Member("佐藤", "sato@example.com", MemberRank.GOLD));
        entityManager.flush();
        entityManager.clear();

        // Act
        Optional<Member> found = memberRepository.findByEmail("sato@example.com");

        // Assert
        Assertions.assertThat(found).hasValueSatisfying(m -> {
            Assertions.assertThat(m.getName()).isEqualTo("佐藤");
            Assertions.assertThat(m.getRank()).isEqualTo(MemberRank.GOLD);
        });
    }

    @Test
    @DisplayName("ランクで絞り込み、名前の順に並べる")
    void findByRankOrderByNameAsc() {
        entityManager.persist(new Member("鈴木", "suzuki@example.com", MemberRank.SILVER));
        entityManager.persist(new Member("伊藤", "ito@example.com", MemberRank.SILVER));
        entityManager.persist(new Member("高橋", "takahashi@example.com", MemberRank.GOLD));
        entityManager.flush();
        entityManager.clear();

        List<Member> members = memberRepository.findByRankOrderByNameAsc(MemberRank.SILVER);

        Assertions.assertThat(members)
                .extracting(Member::getEmail)
                .containsExactly("ito@example.com", "suzuki@example.com");
    }

    @Test
    @DisplayName("名前に keyword を含む会員を返す（@Query）")
    void searchByName() {
        entityManager.persist(new Member("田中一郎", "ichiro@example.com", MemberRank.REGULAR));
        entityManager.persist(new Member("田中花子", "hanako@example.com", MemberRank.GOLD));
        entityManager.persist(new Member("渡辺", "watanabe@example.com", MemberRank.REGULAR));
        entityManager.flush();
        entityManager.clear();

        List<Member> members = memberRepository.searchByName("田中");

        Assertions.assertThat(members).extracting(Member::getName).containsExactly("田中一郎", "田中花子");
    }

    @Test
    @DisplayName("同じメールアドレスは登録できない（一意制約）")
    void duplicateEmail() {
        entityManager.persistAndFlush(new Member("佐藤", "sato@example.com", MemberRank.REGULAR));

        Assertions.assertThatThrownBy(() -> memberRepository.saveAndFlush(
                new Member("佐藤次郎", "sato@example.com", MemberRank.GOLD)))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
