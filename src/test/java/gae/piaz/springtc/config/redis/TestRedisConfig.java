package gae.piaz.springtc.config;

import gae.piaz.springtc.config.postgres.PostgresContainer;
import gae.piaz.springtc.config.redis.RedisContainer;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class TestContainerConfig
{
    public static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:15.1-alpine");
    public static final DockerImageName REDIS_IMAGE = DockerImageName.parse("redis:5.0.3-alpine");


    @Bean
    // This annotation is used to indicate that this bean will not be re-created if the application
    // restarts due to spring-dev-tools.
    // @org.springframework.boot.devtools.restart.RestartScope
    // By default, ServiceConnection will create all applicable connection details beans
    // for a given Container.
    // For example, a PostgreSQLContainer will create both JdbcConnectionDetails and R2dbcConnectionDetails.
    @ServiceConnection(type = JdbcConnectionDetails.class)
    public PostgresContainer postgresContainer()
    {

	final long memoryInBytes = 64L * 1024L * 1024L;
	final long memorySwapInBytes = 128L * 1024L * 1024L;

	return new PostgresContainer(POSTGRES_IMAGE)
		.waitingFor(Wait.forLogMessage(".*PostgreSQL init process complete;.*\\n", 1))
		// The Reusable Containers feature keeps the same containers running between test sessions
		.withReuse(true)
		.withCreateContainerCmdModifier(cmd -> {
		    cmd.withName("postgres");
		    cmd.getHostConfig()
		       .withMemory(memoryInBytes)
		       .withMemorySwap(memorySwapInBytes);
		});
    }

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
