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

import com.sma.model.Student;
import com.sma.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService service;
	
	
	@Autowired
	MessageSource messageSource;
	
	/*
	 * This method will list all existing students.
	 */
	@RequestMapping(value = { "/", "/liststudents" }, method = RequestMethod.GET)
	public String liststudents(ModelMap model) {

		List<Student> students = service.findAllStudents();
		model.addAttribute("students", students);
		return "allstudents";
	}
	
	/*
	 * This method will provide the medium to add a new student.
	 */
	@RequestMapping(value = { "/newstudent" }, method = RequestMethod.GET)
	public String newstudent(ModelMap model) {
		Student student = new Student();
		model.addAttribute("student", student);
		model.addAttribute("edit", false);
		return "studentRegistration";
	}
	
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving student in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newstudent" }, method = RequestMethod.POST)
	public String savestudent(@Valid Student student, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "studentRegistration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [studentId] should be implementing custom @Unique annotation 
		 * and applying it on field [studentId] of Model class [student].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isStudentIdUnique(student.getId(), student.getStudent_id())){
			FieldError ssnError =new FieldError("student","studentId",messageSource.getMessage("non.unique.studentId", new String[]{student.getStudent_id()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "studentRegistration";
		}
		
		service.saveStudent(student);

		model.addAttribute("success", "student " + student.getStudent_first_name() + student.getStudent_last_name()+ " registered successfully");
		return "success";
	}
	

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{studentId}-student" }, method = RequestMethod.GET)
	public String editstudent(@PathVariable String studentId, ModelMap model) {
		Student student = service.findStudentByStudentId(studentId);
		model.addAttribute("student", student);
		model.addAttribute("edit", true);
		return "studentRegistration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{studentId}-student" }, method = RequestMethod.POST)
	public String updatestudent(@Valid Student student, BindingResult result,
			ModelMap model, @PathVariable String studentId) {

		if (result.hasErrors()) {
			return "studentRegistration";
		}

		if(!service.isStudentIdUnique(student.getId(), student.getStudent_id())){
			FieldError ssnError =new FieldError("student","studentId",messageSource.getMessage("non.unique.studentId", new String[]{student.getStudent_id()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "studentRegistration";
		}

		service.updateStudent(student);

		model.addAttribute("success", "student " + student.getStudent_first_name()+ student.getStudent_last_name()+ " updated successfully");
		return "success";
	}
	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{studentId}-student" }, method = RequestMethod.GET)
	public String deleteStudent(@PathVariable String studentId) {
		service.deleteStudentByStudentId(studentId);
		return "redirect:/liststudents";
	}
}
