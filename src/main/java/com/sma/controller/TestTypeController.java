package com.sma.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sma.model.TestType;
import com.sma.service.TestTypeService;

@Controller
@RequestMapping("/testType")
public class TestTypeController {

	@Autowired
	TestTypeService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 /* This method will list all existing TestTypes.*/
	 
	@RequestMapping(value = { "/testType", "/listTestTypes" }, method = RequestMethod.GET)
	public String listTestTypes(ModelMap model) {

		List<TestType> testTypes = service.findAllTestTypes();
		model.addAttribute("testTypes", testTypes);
		return "allTestTypes";
	}
	
	
	  /*This method will provide the medium to add a new TestType.*/
	 
	@RequestMapping(value = { "/newTestType" }, method = RequestMethod.GET)
	public String newTestType(ModelMap model) {
		TestType testType = new TestType();
		model.addAttribute("testType", testType);
		model.addAttribute("edit", false);
		return "testTypeRegistration";
	}
	
	
	
	 /*This method will be called on form submission, handling POST request for
	 saving TestType in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/newTestType" }, method = RequestMethod.POST)
	public String saveTestType(@Valid TestType testType, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "testTypeRegistration";
		}

		
		 /** Preferred way to achieve uniqueness of field [Category] should be implementing custom @Unique annotation 
		 * and applying it on field [Category] of Model class [TestType].
		 * 
		 * Below mentioned peace of category [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 **/ 
		 
		if(!service.isTestTypeIdUnique(testType.getId(), testType.getCategory())){
			FieldError ssnError =new FieldError("testType","category",messageSource.getMessage("non.unique.category", new String[]{testType.getCategory()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "testTypeRegistration";
		}
		
		service.saveTestType(testType);

		model.addAttribute("success", "testType " + testType.getName() + " registered successfully");
		return "success";
	}
	

	
	 /*This method will provide the medium to update an existing employee.*/
	 
	@RequestMapping(value = { "/edit-{category}-testType" }, method = RequestMethod.GET)
	public String editTestType(@PathVariable String category, ModelMap model) {
		TestType testType = service.findTestTypeByCategory(category);
		model.addAttribute("testType", testType);
		model.addAttribute("edit", true);
		return "testTypeRegistration";
	}
	
	
	 /*This method will be called on form submission, handling POST request for
	 updating employee in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/edit-{category}-testType" }, method = RequestMethod.POST)
	public String updateTestType(@Valid TestType testType, BindingResult result,
			ModelMap model, @PathVariable String category) {

		if (result.hasErrors()) {
			return "testTypeRegistration";
		}

		if(!service.isTestTypeIdUnique(testType.getId(), testType.getCategory())){
			FieldError ssnError =new FieldError("testType","category",messageSource.getMessage("non.unique.category", new String[]{testType.getCategory()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "testTypeRegistration";
		}

		service.updateTestType(testType);

		model.addAttribute("success", "testType " + testType.getName()	+ " updated successfully");
		return "success";
	}
	
	
	 /*This method will delete an employee by it's SSN value.*/	 
	@RequestMapping(value = { "/delete-{category}-testType" }, method = RequestMethod.GET)
	public String deleteTestType(@PathVariable String category) {
		service.deleteTestTypeById(category);
		return "redirect:/listTestTypes";
	}

}

