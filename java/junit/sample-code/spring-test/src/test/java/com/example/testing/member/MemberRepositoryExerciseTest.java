package com.example.testing.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * 演習6：MemberRepository.existsByEmail のテストを書く
 * <p>
 * 仕様：メールアドレスが登録済みなら true、登録されていなければ false を返す
 * <p>
 * テストデータは TestEntityManager で登録し、flush と clear をしてから検索する。
 * テスト対象のリポジトリ（save など）では登録しない。
 */
@DataJpaTest
@Testcontainers
@DisplayName("演習6：MemberRepository のメールアドレス確認")
class MemberRepositoryExerciseTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17-alpine");

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestEntityManager entityManager;

    // TODO 演習6-1：会員（佐藤・sato@example.com・GOLD）を TestEntityManager で登録し、
    //   existsByEmail("sato@example.com") が true を返すことを確かめる
    @Test
    @DisplayName("登録済みのメールアドレスなら true")
    void existsByEmail() {
        entityManager.persist(new Member("佐藤", "sato@example.com", MemberRank.GOLD));
        entityManager.flush();
        entityManager.clear();

        boolean exists = memberRepository.existsByEmail("sato@example.com");

        Assertions.assertThat(exists).isTrue();
    }

    // TODO 演習6-2：同じ会員を登録し、
    //   existsByEmail("suzuki@example.com") が false を返すことを確かめる
    @Test
    @DisplayName("登録されていないメールアドレスなら false")
    void notExistsByEmail() {
        entityManager.persist(new Member("佐藤", "sato@example.com", MemberRank.GOLD));
        entityManager.flush();
        entityManager.clear();

        boolean exists = memberRepository.existsByEmail("suzuki@example.com");

        Assertions.assertThat(exists).isFalse();
    }
}
