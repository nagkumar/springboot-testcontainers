package gae.piaz.springtc.config.flask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.io.IOException;

@Configuration
public class CreateFlaskInstance
{
    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    //@ServiceConnection we don't need auto-connection in this case as we are reaching this
    //through environment variables look at method gae.piaz.springtc.service.CustomerService.fetchFromFlask
    public FlaskContainer flaskContainer() throws IOException
    {
	final long memoryInBytes = 32L * 1024L * 1024L;
	final long memorySwapInBytes = 64L * 1024L * 1024L;

	Resource resource = resourceLoader.getResource("classpath:flask/Dockerfile");
	return new FlaskContainer(
		new ImageFromDockerfile().withDockerfile(resource.getFile().toPath()))
		// 5000 is the standard port of flask. check "FlaskExposePort" for details
		.withExposedPorts(5000)
		.withCreateContainerCmdModifier(cmd ->
						{
						    cmd.withName("flaskapp");
						    cmd.getHostConfig()
						       .withMemory(memoryInBytes)
						       .withMemorySwap(memorySwapInBytes);
						});
    }
}
