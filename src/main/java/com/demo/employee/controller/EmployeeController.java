package com.demo.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.controller.bean.EmployeeBean;
import com.demo.employee.service.EmployeeImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/employee/")
public class EmployeeController {

	@Autowired
	EmployeeImpl employeeImpl;

	/**
	 * Controller used to get employee list To get employee list
	 * 
	 * @author Karthick
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "list")
	public List<EmployeeBean> getEmployeeList() throws Exception {
		return employeeImpl.getEmployeeList();
	}

	/**
	 * Controller used to save employee details To save
	 * 
	 * @author Karthick
	 * @param employee
	 */
	@PostMapping(value = "save")
	public EmployeeBean postEmployee(@RequestBody EmployeeBean employee) throws Exception {

		return employeeImpl.saveEmployee(employee);
	}

	@PutMapping(value = "update")
	public EmployeeBean updateEmployee(@RequestBody EmployeeBean employeeInput) throws Exception {

		return employeeImpl.updateEmployee(employeeInput);
	}

	/**
	 * Controller used to delete employee details
	 * 
	 * @author Karthick
	 * @param employeeId
	 * @throws Exception
	 * 
	 */
	@DeleteMapping(value = "delete/{employeeId}")
	public void deleteEmployee(@PathVariable("employeeId") Integer employeeId) throws Exception {
		employeeImpl.deleteEmployee(employeeId);
		return;
	}

}