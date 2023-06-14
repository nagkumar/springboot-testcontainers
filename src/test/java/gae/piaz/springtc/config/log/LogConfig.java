package gae.piaz.springtc.config.log;

import gae.piaz.springtc.TestApplication;
import gae.piaz.springtc.config.flask.FlaskContainer;
import gae.piaz.springtc.config.postgres.PostgresContainer;
import gae.piaz.springtc.config.redis.RedisContainer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

@Configuration
@Service
public class LogConfig
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestApplication.class);

    @Autowired
    private PostgresContainer postgresContainer;

    @Autowired
    private RedisContainer redisContainer;

    @Autowired
    private KafkaContainer kafkaContainer;

    @Autowired
    private FlaskContainer flaskContainer;

    @PostConstruct
    public void init()
    {
	// Attaching the log of the Containers we create to the log of the Spring-Boot app.
	Slf4jLogConsumer logConslSlf4jLogConsumermer = new Slf4jLogConsumer(LOGGER);
	logConslSlf4jLogConsumermer.withPrefix("TC-LOG--->");

	postgresContainer.followOutput(logConslSlf4jLogConsumermer);
	redisContainer.followOutput(logConslSlf4jLogConsumermer);
	kafkaContainer.followOutput(logConslSlf4jLogConsumermer);
	flaskContainer.followOutput(logConslSlf4jLogConsumermer);
    }


}
