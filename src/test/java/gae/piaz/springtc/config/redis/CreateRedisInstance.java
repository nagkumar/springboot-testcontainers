package gae.piaz.springtc.config.redis;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class CreateRedisInstance
{
    private static final DockerImageName REDIS_IMAGE = DockerImageName.parse("redis:5.0.3-alpine");

    @Bean
    // Autoconfig for ServiceConnection for redis images works only if Container is named "redis"
    @ServiceConnection(name = "redis")
    public RedisContainer redisContainer()
    {
	final long memoryInBytes = 32L * 1024L * 1024L;
	final long memorySwapInBytes = 64L * 1024L * 1024L;

	return new RedisContainer(REDIS_IMAGE)
		.withExposedPorts(6379)
		.withReuse(true)
		.withCreateContainerCmdModifier(cmd -> {
		    cmd.withName("redis");
		    cmd.getHostConfig()
		       .withMemory(memoryInBytes)
		       .withMemorySwap(memorySwapInBytes);
		});
    }
}
