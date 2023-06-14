package gae.piaz.springtc.controller;

import gae.piaz.springtc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class CustomerHttpController
{
    private static final String FOUND_CUSTOMERS = "Found {} customers";

    private final CustomerService customerService;

    @GetMapping(value = "/customersPG", produces = "application/json")
    public List<CustomerDTO> customers()
    {
	List<CustomerDTO> customers = this.customerService.fetchFromDB();
	log.info(FOUND_CUSTOMERS, customers.size());
	return customers;
    }

    @GetMapping(value = "/customersFlask", produces = "application/json")
    public List<CustomerDTO> customersExt()
    {
	List<CustomerDTO> customers = this.customerService.fetchFromFlask();
	log.info(FOUND_CUSTOMERS, customers.size());
	return customers;
    }

    @GetMapping("/customersRedis/{name}")
    public List<CustomerDTO> byName(@PathVariable String name)
    {
	List<CustomerDTO> customers = this.customerService.fetchFromCacheByName(name);
	log.info(FOUND_CUSTOMERS, customers.size());
	return customers;
    }

    @PostMapping(value = "/customersKafka", consumes = "application/json")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO)
    {
	customerService.publishOnKafka(customerDTO);
	log.info("Saved asynchronously a new customer");
    }
}
