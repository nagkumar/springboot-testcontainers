package gae.piaz.springtc.cfg.redis;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public final class RedisContainer extends GenericContainer<RedisContainer>
{
    public RedisContainer(final DockerImageName aDockerImageName)
    {
	super(aDockerImageName);
    }
}
