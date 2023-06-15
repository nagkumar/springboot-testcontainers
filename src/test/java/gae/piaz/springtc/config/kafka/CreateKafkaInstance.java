package gae.piaz.springtc.config.kafka;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class CreateKafkaInstance
{
    private static final DockerImageName KAFKA_IMAGE = DockerImageName.parse("confluentinc/cp-kafka:7.2.1");

    @Bean
    @ServiceConnection
    public KafkaContainer kafkaContainer()
    {
	final long memoryInBytes = 512L * 1024L * 1024L;
	final long memorySwapInBytes = 1024L * 1024L * 1024L;

	return new KafkaContainer(KAFKA_IMAGE)
		.withReuse(true).withCreateContainerCmdModifier(cmd -> {
		    cmd.withName("kafka");
		    cmd.getHostConfig()
		       .withMemory(memoryInBytes)
		       .withMemorySwap(memorySwapInBytes);
		});
    }
}
