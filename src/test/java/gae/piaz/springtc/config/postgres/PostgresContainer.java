package gae.piaz.springtc.config.postgres;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

// Custom classes to avoid "Raw use of parameterized class 'GenericContainer'"
// Test-Container use self-typing mechanism to make fluent method works even with extended classes.
// https://stackoverflow.com/a/57077189
public final class PostgresContainer extends PostgreSQLContainer<PostgresContainer>
{
    public PostgresContainer(final DockerImageName aDockerImageName)
    {
	super(aDockerImageName);
    }
}
