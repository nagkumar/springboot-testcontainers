package gae.piaz.springtc.config.flask;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

public class PythonContainer extends GenericContainer<PythonContainer>
{
    public PythonContainer(ImageFromDockerfile image)
    {
	super(image);
    }
}
