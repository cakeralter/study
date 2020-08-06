package cc.caker.springboot.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author cakeralter
 * @since 2020/8/5
 */
@EnableCaching
@Configuration
public class RedisConfig {

    @Bean
    public GenericFastJsonRedisSerializer genericFastJsonRedisSerializer() {
        return new GenericFastJsonRedisSerializer();
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, T> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(genericFastJsonRedisSerializer());
        template.setValueSerializer(stringRedisSerializer());
        template.setHashKeySerializer(genericFastJsonRedisSerializer());
        template.setHashValueSerializer(stringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}
