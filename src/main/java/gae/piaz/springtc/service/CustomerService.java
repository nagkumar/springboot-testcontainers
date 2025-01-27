package gae.piaz.springtc.service;

import gae.piaz.springtc.cfg.redis.RedisCFG;
import gae.piaz.springtc.controller.CustomerDTO;
import gae.piaz.springtc.data.CustomerRepository;
import gae.piaz.springtc.kafka.publisher.CustomerEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService
{
    private final CustomerRepository customerRepository;
    private final CustomerEventPublisher publisher;
    private final Environment env;

    public List<CustomerDTO> fetchFromDB()
    {
	return customerRepository.findAll()
				 .stream()
				 .map(customer ->
					      new CustomerDTO(customer.getName(), customer.getId()))
				 .toList();
    }

    @Cacheable(RedisCFG.CUSTOMER_CACHE)
    public List<CustomerDTO> fetchFromCacheByName(final String aName)
    {
	log.info("------ Hitting database & not using cache! ------ ");
	return customerRepository.findByNameIgnoreCase(aName)
				 .stream()
				 .map(customer ->
					      new CustomerDTO(customer.getName(), customer.getId()))
				 .toList();
    }

    public List<CustomerDTO> fetchFromFlask()
    {
	final String lUrl = "http://" + env.getProperty("flask.server.host") + ":" +
			    env.getProperty("flask.server.port") + "/customersFlask";

	final RestTemplate lRestTemplate = new RestTemplate();
	final ResponseEntity<CustomerDTO[]> lResponseEntity = lRestTemplate.getForEntity(lUrl, CustomerDTO[].class);
	return Arrays.asList(Objects.requireNonNull(lResponseEntity.getBody()));
    }

    public void publishOnKafka(final CustomerDTO aCustomerDTO)
    {
	publisher.publishCustomerCreatedEvent(aCustomerDTO);
    }
}
