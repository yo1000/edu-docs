package com.example.testing.product;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * 起動時にトピックを作っておく（リスナーが存在しないトピックを待ち続けないように）。
 * トピック名は spring.kafka.template.default-topic の値を使う。
 */
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic stockArrivalsTopic(KafkaProperties kafkaProperties) {
        return TopicBuilder.name(kafkaProperties.getTemplate().getDefaultTopic())
                .partitions(1)
                .replicas(1)
                .build();
    }
}
