package com.Jorge.Aneury.practica6.config;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSession;
import org.springframework.session.hazelcast.HazelcastIndexedSessionRepository;
import org.springframework.session.hazelcast.HazelcastSessionSerializer;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@Configuration
public class HazelCastConfig {
    private final static String SESSIONS_MAP_NAME = "SESSION-HAZELCAST";

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        config.setClusterName("spring-session-cluster");

        // Add this attribute to be able to query sessions by their PRINCIPAL_NAME_ATTRIBUTE's
        AttributeConfig attributeConfig = new AttributeConfig()
                .setName(HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
                .setExtractorClassName(HazelcastIndexedSessionRepository.class.getName());

        // Configure the sessions map
        config.getMapConfig(SESSIONS_MAP_NAME)
                .addAttributeConfig(attributeConfig).addIndexConfig(
                        new IndexConfig(IndexType.HASH, HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE));

        // Use custom serializer to de/serialize sessions faster. This is optional.
        SerializerConfig serializerConfig = new SerializerConfig();
        serializerConfig.setImplementation(new HazelcastSessionSerializer()).setTypeClass(MapSession.class);
        config.getSerializationConfig().addSerializerConfig(serializerConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}
