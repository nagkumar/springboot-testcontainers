package gae.piaz.springtc.cfg.redis;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@Configuration
public class RedisCFG
{
    public static final String CUSTOMER_CACHE = "customerCache";

    @Bean
    public RedisCacheConfiguration cacheConfiguration()
    {
	return RedisCacheConfiguration.defaultCacheConfig()
				      .entryTtl(Duration.ofMinutes(60));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer()
    {
	return (builder) -> builder
		.withCacheConfiguration(CUSTOMER_CACHE,
					RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
    }
}
