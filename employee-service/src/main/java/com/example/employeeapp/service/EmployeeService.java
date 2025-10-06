package com.example.employeeapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	private RestTemplate restTemplate;
	
	public EmployeeResponse getEmployeeById(int id)
	{
		//addressResponse->set data by making restapi call
		//AddressResponse addressResponse = new AddressResponse();
		
		//employee->EmployeeResponse
		Employee employee = EmployeeRepo.findById(id).get();
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
		AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8081/address-app/api/address/{id}",AddressResponse.class, id);
		
		employeeResponse.setAddressResponse(addressResponse);
		
		
		
//		EmployeeResponse employeeResponse = new EmployeeResponse();
//		employeeResponse.setId(employee.getId());
//		employeeResponse.setName(employee.getName());
//		employeeResponse.setEmail(employee.getEmail());
		
		
		return employeeResponse;
	}
}
