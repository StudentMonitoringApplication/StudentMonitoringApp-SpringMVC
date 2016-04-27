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

import com.sma.model.StudentSubject;
import com.sma.service.StudentSubjectService;

@Controller
@RequestMapping("/StudentSubject")
public class StudentSubjectController {

	@Autowired
	StudentSubjectService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 /* This method will list all existing Subjects.*/
	 
	@RequestMapping(value = { "/StudentSubject", "/listStudentSubject" }, method = RequestMethod.GET)
	public String listHeadsOfDepartment(ModelMap model) {

		List<StudentSubject> StudentSubject = service.findAllStudentSubjects();
		model.addAttribute("StudentSubject", StudentSubject);
		return "allStudentSubject";
	}
	
	
	  /*This method will provide the medium to add a new Subject.*/
	 
	@RequestMapping(value = { "/newStudentSubject" }, method = RequestMethod.GET)
	public String newStudentSubject(ModelMap model) {
		StudentSubject StudentSubject = new StudentSubject();
		model.addAttribute("StudentSubject", StudentSubject);
		model.addAttribute("edit", false);
		return "StudentSubjectRegistration";
	}
	
	
	
	 /*This method will be called on form submission, handling POST request for
	 saving Subject in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/newStudentSubject" }, method = RequestMethod.POST)
	public String saveStudentSubject(@Valid StudentSubject studentSubject, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "StudentSubjectRegistration";
		}

		
		 /** Preferred way to achieve uniqueness of field [Code] should be implementing custom @Unique annotation 
		 * and applying it on field [Code] of Model class [Subject].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 **/ 
		 
		if(!service.isStudentSubjectUnique(studentSubject.getId(), studentSubject.getStudent_id(), studentSubject.getSubject_id())){
			FieldError ssnError =new FieldError("StudentSubject","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(studentSubject.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "StudentSubjectRegistration";
		}
		
		service.saveStudentSubject(studentSubject);

		model.addAttribute("success", "StudentSubject " + studentSubject.getId() + " registered successfully");
		return "success";
	}
	

	
	 /*This method will provide the medium to update an existing employee.*/
	 
	@RequestMapping(value = { "/edit-{student_id}-{subject_id}-studentSubject" }, method = RequestMethod.GET)
	public String editSubject(@PathVariable int student_id, @PathVariable int subject_id, ModelMap model) {
		StudentSubject studentSubject = service.findStudentSubject(student_id, subject_id);
		model.addAttribute("StudentSubject", studentSubject);
		model.addAttribute("edit", true);
		return "studentSubjectRegistration";
	}
	
	
	 /*This method will be called on form submission, handling POST request for
	 updating employee in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/edit-{student_id}-{subject_id}-studentSubject" }, method = RequestMethod.POST)
	public String updateSubject(@Valid StudentSubject studentSubject, BindingResult result,
			ModelMap model, @PathVariable int student_id, @PathVariable int subject_id) {

		if (result.hasErrors()) {
			return "studentSubjectRegistration";
		}

		if(!service.isStudentSubjectUnique(studentSubject.getId(), studentSubject.getStudent_id(), studentSubject.getSubject_id())){
			FieldError ssnError =new FieldError("StudentSubject","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(studentSubject.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "StudentSubjectRegistration";
		}

		service.updateStudentSubject(studentSubject);

		model.addAttribute("success", "StudentSubject " + studentSubject.getId()	+ " updated successfully");
		return "success";
	}
	
	
	 /*This method will delete an employee by it's SSN value.*/	 
	@RequestMapping(value = { "/delete-{id}-studentSubject" }, method = RequestMethod.GET)
	public String deleteSubject(@PathVariable int id) {
		service.deleteStudentSubjectId(id);
		return "redirect:/listStudentSubject";
	}

}
