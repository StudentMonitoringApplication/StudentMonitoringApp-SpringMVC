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

import com.sma.model.StudentStream;
import com.sma.service.StudentStreamService;

@Controller
@RequestMapping("/studentStream")
public class StudentStreamController {
	
	@Autowired
	StudentStreamService service;
	
	
	@Autowired
	MessageSource messageSource;
	
	/*
	 * This method will list all existing studentStreams.
	 */
	@RequestMapping(value = { "/", "/listStudentStreams" }, method = RequestMethod.GET)
	public String listStudentStreams(ModelMap model) {

		List<StudentStream> studentStreams = service.findAllStudentStreams();
		model.addAttribute("studentStreams", studentStreams);
		return "allstudentStreams";
	}
	
	/*
	 * This method will provide the medium to add a new studentStream.
	 */
	@RequestMapping(value = { "/newStudentStream" }, method = RequestMethod.GET)
	public String newStudentStream(ModelMap model) {
		StudentStream studentStream = new StudentStream();
		model.addAttribute("studentStream", studentStream);
		model.addAttribute("edit", false);
		return "studentStreamRegistration";
	}
	
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving studentStream in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newStudentStream" }, method = RequestMethod.POST)
	public String saveStudentStream(@Valid StudentStream studentStream, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "studentStreamRegistration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [studentStreamId] should be implementing custom @Unique annotation 
		 * and applying it on field [studentStreamId] of Model class [StudentStream].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isStudentStreamUnique(studentStream.getId(), studentStream.getStudent_id(), studentStream.getClass_id())){
			FieldError ssnError =new FieldError("studentStream","studentStreamId",messageSource.getMessage("non.unique.Id", new String[]{String.valueOf(studentStream.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "studentStreamRegistration";
		}
		
		service.saveStudentStream(studentStream);

		model.addAttribute("success", "StudentStream " + studentStream.getId() + " registered successfully");
		return "success";
	}
	

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{studentStreamId}-studentStream" }, method = RequestMethod.GET)
	public String editStudentStream(@PathVariable int student_id, int class_id, ModelMap model) {
		StudentStream studentStream1 = service.findStudentStream(student_id, class_id);
		model.addAttribute("studentStream", studentStream1);
		model.addAttribute("edit", true);
		return "studentStreamRegistration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{studentStreamId}-studentStream" }, method = RequestMethod.POST)
	public String updateStudentStream(@Valid StudentStream studentStream, BindingResult result,
			ModelMap model, @PathVariable String studentStreamId) {

		if (result.hasErrors()) {
			return "studentStreamRegistration";
		}

		if(!service.isStudentStreamUnique(studentStream.getId(), studentStream.getStudent_id(), studentStream.getClass_id())){
			FieldError ssnError =new FieldError("studentStream","studentStreamId",messageSource.getMessage("non.unique.Id", new String[]{String.valueOf(studentStream.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "studentStreamRegistration";
		}

		service.updateStudentStream(studentStream);

		model.addAttribute("success", "StudentStream " + studentStream.getId()	+ " updated successfully");
		return "success";
	}
	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{studentStreamId}-studentStream" }, method = RequestMethod.GET)
	public String deleteStudentStream(@PathVariable int id) {
		service.deleteStudentStreamId(id);
		return "redirect:/listStudentStreams";
	}

}
