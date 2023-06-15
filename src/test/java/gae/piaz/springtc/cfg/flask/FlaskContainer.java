package gae.piaz.springtc.cfg.flask;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

public final class FlaskContainer extends GenericContainer<FlaskContainer>
{
    public FlaskContainer(final ImageFromDockerfile aImageFromDockerfile)
    {
	super(aImageFromDockerfile);
    }
}
