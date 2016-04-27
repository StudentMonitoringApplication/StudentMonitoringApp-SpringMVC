package com.sma.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sma.model.Test;
import com.sma.service.TestService;

@Controller
@RequestMapping("/Test")
public class TestController {

	@Autowired
	TestService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 /* This method will list all existing Subjects.*/
	 
	@RequestMapping(value = { "/Test", "/listTest" }, method = RequestMethod.GET)
	public String listHeadsOfDepartment(ModelMap model) {

		List<Test> Test = service.findAllTests();
		model.addAttribute("Test", Test);
		return "allTest";
	}
	
	
	  /*This method will provide the medium to add a new Subject.*/
	 
	@RequestMapping(value = { "/newTest" }, method = RequestMethod.GET)
	public String newTest(ModelMap model) {
		Test Test = new Test();
		model.addAttribute("Test", Test);
		model.addAttribute("edit", false);
		return "TestRegistration";
	}
	
	
	
	 /*This method will be called on form submission, handling POST request for
	 saving Subject in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/newTest" }, method = RequestMethod.POST)
	public String saveTest(@Valid Test test, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "TestRegistration";
		}

		
		 /** Preferred way to achieve uniqueness of field [Code] should be implementing custom @Unique annotation 
		 * and applying it on field [Code] of Model class [Subject].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 **/ 
		//(int term, LocalDate year, int test_type_id)
		if(!service.isTestIdUnique(test.getId(), test.getTerm(), test.getYear(), test.getTest_type_id())){
			FieldError ssnError =new FieldError("Test","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(test.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "TestRegistration";
		}
		
		service.saveTest(test);

		model.addAttribute("success", "Test " + test.getId() + " registered successfully");
		return "success";
	}
	

	
	 /*This method will provide the medium to update an existing employee.*/
	 
	@RequestMapping(value = { "/edit-{term}-{year}-{test_type_id}-test" }, method = RequestMethod.GET)
	public String editSubject(@PathVariable int term, @PathVariable LocalDate year,  @PathVariable int test_type_id, ModelMap model) {
		Test test = service.findTest(term, year, test_type_id);
		model.addAttribute("Test", test);
		model.addAttribute("edit", true);
		return "testRegistration";
	}
	
	
	 /*This method will be called on form submission, handling POST request for
	 updating employee in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/edit-{term}-{year}-{test_type_id}-test" }, method = RequestMethod.POST)
	public String updateSubject(@Valid Test test, BindingResult result,
			ModelMap model, @PathVariable int term, @PathVariable LocalDate year, @PathVariable int test_type_id) {

		if (result.hasErrors()) {
			return "testRegistration";
		}

		if(!service.isTestIdUnique(test.getId(), test.getTerm(), test.getYear(), test.getTest_type_id())){
			FieldError ssnError =new FieldError("Test","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(test.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "TestRegistration";
		}

		service.updateTest(test);

		model.addAttribute("success", "Test " + test.getId()	+ " updated successfully");
		return "success";
	}
	
	
	 /*This method will delete an employee by it's SSN value.*/	 
	@RequestMapping(value = { "/delete-{id}-test" }, method = RequestMethod.GET)
	public String deleteSubject(@PathVariable int id) {
		service.deleteTestById(id);
		return "redirect:/listTest";
	}

}
