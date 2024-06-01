package com.example.employeeinformation.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employeeinformation.Entity.employeeTable;
import com.example.employeeinformation.Repository.employeerepo;

@Controller
public class employeecontroller {

	@Autowired
	employeerepo employeerepoo;

	@GetMapping(value = "/userform")
	public String userform() {
		return "userform";
	}

	@PostMapping(value = "/newEmployeeDetails")
	public String addEmployeeDetails(@ModelAttribute employeeTable employeeData, Model model) {

		employeerepoo.save(employeeData);
		model.addAttribute("allEmployees", employeerepoo.findAll());
		return "homepage";
	}

	@GetMapping(value = "/homepage")
	public String getAllEmployees(Model model) {

		List<employeeTable> allEmployees = employeerepoo.findAll();
		model.addAttribute("allEmployees", allEmployees);

		return "homePage";
	}

	// show create user html page
	@GetMapping(value = "/createuser")
	public String createpage() {

		return "userform";
	}

	// update page html
	@PostMapping(value = "/updateuser/{id}")
	public String updatepage(@PathVariable long id, Model model) {
		Optional<employeeTable> findId = employeerepoo.findById(id);

		model.addAttribute("allEmployees", findId.get());
		return "updateuserform";
	}

	// individual user

	@GetMapping(value = "/getOneEmployee/{id}")
	public employeeTable getOneEmployee(@PathVariable long id) {
		Optional<employeeTable> findId = employeerepoo.findById(id);
		if (findId.isPresent()) {
			return findId.get();
		} else {
			return null;
		}

	}

	// update
	@PostMapping(value = "/updateEmployeeDetails/{id}")
	public String updateEmployeeDetails(@PathVariable long id, @ModelAttribute employeeTable employeeData,
			Model model) {

		Optional<employeeTable> findId = employeerepoo.findById(id);

		if (findId.isPresent()) {

			findId.get().setId(employeeData.getId());
			findId.get().setFirstName(employeeData.getFirstName());
			findId.get().setLastName(employeeData.getLastName());
			findId.get().setEmail(employeeData.getEmail());
			findId.get().setAddress(employeeData.getAddress());

			employeerepoo.save(findId.get());
			model.addAttribute("allEmployees", employeerepoo.findAll());
			return "homepage";
		} else {
			return null;
		}

	}

	// delete
	@PostMapping(value = "/deleteEmployeeDetails/{id}")
	public String deleteEmployeeDetails(@PathVariable long id, Model model) {
		Optional<employeeTable> findId = employeerepoo.findById(id);

		if (findId.isPresent()) {
			employeerepoo.deleteById(id);
			model.addAttribute("allEmployees", employeerepoo.findAll());
			return "homePage";
		} else {
			return "homePage";
		}
	}

}
