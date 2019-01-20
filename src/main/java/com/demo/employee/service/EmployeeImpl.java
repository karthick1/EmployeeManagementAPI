package com.demo.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employee.controller.bean.EmployeeBean;
import com.demo.employee.dao.model.Employee;
import com.demo.employee.dao.repo.EmployeeRepo;
import com.demo.employee.util.Constants;

@Service
public class EmployeeImpl {

	@Autowired
	EmployeeRepo employeeRepo;

	/**
	 * Method used to fetch complete employee list 
	 * 
	 * @author Karthick1
	 * @return employee list
	 */
	public List<EmployeeBean> getEmployeeList() throws Exception {
		List<EmployeeBean> employeeBeanList = new ArrayList<EmployeeBean>();
		List<Employee> employeeList = employeeRepo.findAll();
		if (employeeList.size() > 0) {
			for (Employee empEntity : employeeList) {
				EmployeeBean empBean = new EmployeeBean();
				BeanUtils.copyProperties(empBean, empEntity);
				employeeBeanList.add(empBean);
			}
		}
		return employeeBeanList;
	}

	/**
	 * This method used to save employee data
	 * 
	 * @author Karthick
	 * @param employee
	 */
	public EmployeeBean saveEmployee(EmployeeBean employeeBean) throws Exception {
		try {
			if (employeeBean != null && ValidateEmployee(employeeBean)) {
				Employee employee = new Employee();
				BeanUtils.copyProperties(employee, employeeBean);
				employee.setActive(true);
				employee = employeeRepo.save(employee);
				if (employee != null) {
					BeanUtils.copyProperties(employeeBean, employee);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeBean;
	}

	/**
	 * Method to validate Required fields
	 * 
	 * @param employeeBean
	 * @return
	 * @throws Exception
	 */
	private boolean ValidateEmployee(EmployeeBean employeeBean) throws Exception {
		if (checkIfNull(employeeBean.getEmpName())) {
			throw new Exception(Constants.EMPLOYEE_NAME_REQUIRED);
		}
		if (employeeBean.getMobileNo() == 0) {
			throw new Exception(Constants.EMPLOYEE_MOBILE_REQUIRED);
		}
		if (checkIfNull(employeeBean.getAddress())) {
			throw new Exception(Constants.EMPLOYEE_ADDRESS_REQUIRED);
		}
		if (checkIfNull(employeeBean.getEmailId())) {
			throw new Exception(Constants.EMPLOYEE_EMAIL_REQUIRED);
		}
		return true;
	}

	/**
	 * Method used to update EMployee
	 * 
	 * @param employeeInput
	 * @return employeeInput
	 * @throws Exception
	 */
	public EmployeeBean updateEmployee(EmployeeBean employeeInput) throws Exception {
		Employee employee = null;
		if (employeeRepo.findByEmpId(employeeInput.getEmpId()) != null) {
			if (ValidateEmployee(employeeInput)) {
				employee = new Employee();
				BeanUtils.copyProperties(employee, employeeInput);
				employee.setActive(true);
				employee = employeeRepo.save(employee);
				if (employee != null) {
					BeanUtils.copyProperties(employeeInput, employee);
				}
			}
		} else {
			throw new Exception(Constants.EMPLOYEE_NOT_EXIST_REQUIRED);
		}
		return employeeInput;
	}

	//
	/**
	 * To delete employee by employee id
	 * 
	 * @author Karthick
	 * @param employeeId
	 */
	public String deleteEmployee(Integer employeeId) throws Exception {
		if (employeeId != null) {
			Employee employee = employeeRepo.findByEmpId(employeeId);
			if (employee != null) {
				employeeRepo.delete(employeeId);
			} else {
				throw new Exception(Constants.EMPLOYEE_NOT_EXIST_REQUIRED);
			}
		}
		return null;
	}

	/**
	 * @param str
	 * @return Method to check if the string is null
	 */
	private boolean checkIfNull(String str) {
		if (str == null || str.equals("") || str.equalsIgnoreCase("null"))
			return true;
		else
			return false;
	}
}
