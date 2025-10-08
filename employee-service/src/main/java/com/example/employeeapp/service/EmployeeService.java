package com.example.employeeapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.employeeapp.entity.Employee;
import com.example.employeeapp.repo.EmployeeRepo;
import com.example.employeeapp.response.AddressResponse;
import com.example.employeeapp.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo EmployeeRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WebClient webClient;
	
	
	// @Autowired
	//private RestTemplate restTemplate;

//	@Value("${addressservice.base.url}")
//	private String addressBaseURL;

//	public EmployeeService(@Value("${addressservice.base.url}") String addressBaseURL, RestTemplateBuilder builder) {
//
//		this.restTemplate = builder.rootUri(addressBaseURL).build();
//
//	}

	public EmployeeResponse getEmployeeById(int id) {
		// addressResponse->set data by making restapi call
		// AddressResponse addressResponse = new AddressResponse();

		// employee->EmployeeResponse
		Employee employee = EmployeeRepo.findById(id).get(); // db call->10 sec.
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

		
		//rest template use
		//AddressResponse addressResponse = restTemplate.getForObject("/address/{id}", AddressResponse.class, id);      // external api call
		
		//web client use
		AddressResponse addressResponse = webClient
												.get()
												.uri("/address/"+id)
												.retrieve()
												.bodyToMono(AddressResponse.class)
												.block();
		
		
		employeeResponse.setAddressResponse(addressResponse);

//		EmployeeResponse employeeResponse = new EmployeeResponse();
//		employeeResponse.setId(employee.getId());
//		employeeResponse.setName(employee.getName());
//		employeeResponse.setEmail(employee.getEmail());

		return employeeResponse;
	}

//	private AddressResponse callingAddressServiceUsingRESTTemplate(int id) {
//		return restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
//	}
}
