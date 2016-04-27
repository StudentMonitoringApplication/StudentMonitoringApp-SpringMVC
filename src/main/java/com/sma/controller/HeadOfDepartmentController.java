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

import com.sma.model.HeadOfDepartment;
import com.sma.service.HeadOfDepartmentService;

@Controller
@RequestMapping("/headOfDepartment")
public class HeadOfDepartmentController {

	@Autowired
	HeadOfDepartmentService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 /* This method will list all existing Subjects.*/
	 
	@RequestMapping(value = { "/headOfDepartment", "/listHeadsOfDepartment" }, method = RequestMethod.GET)
	public String listHeadsOfDepartment(ModelMap model) {

		List<HeadOfDepartment> headOfDepartment = service.findAllHeadOfDepartments();
		model.addAttribute("headOfDepartment", headOfDepartment);
		return "allHeadsOfDepartment";
	}
	
	
	  /*This method will provide the medium to add a new Subject.*/
	 
	@RequestMapping(value = { "/newHeadOfDepartment" }, method = RequestMethod.GET)
	public String newHeadOfDepartment(ModelMap model) {
		HeadOfDepartment headOfDepartment = new HeadOfDepartment();
		model.addAttribute("headOfDepartment", headOfDepartment);
		model.addAttribute("edit", false);
		return "headOfDepartmentRegistration";
	}
	
	
	
	 /*This method will be called on form submission, handling POST request for
	 saving Subject in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/newHeadOfDepartment" }, method = RequestMethod.POST)
	public String saveHeadOfDepartment(@Valid HeadOfDepartment headOfDepartment, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "headOfDepartmentRegistration";
		}

		
		 /** Preferred way to achieve uniqueness of field [Code] should be implementing custom @Unique annotation 
		 * and applying it on field [Code] of Model class [Subject].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 **/ 
		 
		if(!service.isHeadOfDepartmentIdUnique(headOfDepartment.getId(), headOfDepartment.getTeacher_id(), headOfDepartment.getSubject_id())){
			FieldError ssnError =new FieldError("headOfDepartment","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(headOfDepartment.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "headOfDepartmentRegistration";
		}
		
		service.saveHeadOfDepartment(headOfDepartment);

		model.addAttribute("success", "headOfDepartment " + headOfDepartment.getId() + " registered successfully");
		return "success";
	}
	

	
	 /*This method will provide the medium to update an existing employee.*/
	 
	@RequestMapping(value = { "/edit-{teacher_id}-{subject_id}-headOfDepartment" }, method = RequestMethod.GET)
	public String editSubject(@PathVariable int teacher_id, @PathVariable int subject_id, ModelMap model) {
		HeadOfDepartment headOfDepartment = service.findHeadOfDepartment(teacher_id, subject_id);
		model.addAttribute("headOfDepartment", headOfDepartment);
		model.addAttribute("edit", true);
		return "headOfDepartmentRegistration";
	}
	
	
	 /*This method will be called on form submission, handling POST request for
	 updating employee in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/edit-{teacher_id}-{subject_id}-headOfDepartment" }, method = RequestMethod.POST)
	public String updateSubject(@Valid HeadOfDepartment headOfDepartment, BindingResult result,
			ModelMap model, @PathVariable int teacher_id, @PathVariable int subject_id) {

		if (result.hasErrors()) {
			return "headOfDepartmentRegistration";
		}

		if(!service.isHeadOfDepartmentIdUnique(headOfDepartment.getId(), headOfDepartment.getTeacher_id(), headOfDepartment.getSubject_id())){
			FieldError ssnError =new FieldError("headOfDepartment","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(headOfDepartment.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "headOfDepartmentRegistration";
		}

		service.updateHeadOfDepartment(headOfDepartment);

		model.addAttribute("success", "headOfDepartment " + headOfDepartment.getId()	+ " updated successfully");
		return "success";
	}
	
	
	 /*This method will delete an employee by it's SSN value.*/	 
	@RequestMapping(value = { "/delete-{id}-headOfDepartment" }, method = RequestMethod.GET)
	public String deleteSubject(@PathVariable int id) {
		service.deleteHeadOfDepartmentById(id);
		return "redirect:/listHeadOfDepartment";
	}

}
