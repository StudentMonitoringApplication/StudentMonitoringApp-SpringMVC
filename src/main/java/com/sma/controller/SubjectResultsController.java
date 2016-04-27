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

import com.sma.model.SubjectResults;
import com.sma.service.SubjectResultsService;

@Controller
@RequestMapping("/subjectResults")
public class SubjectResultsController {
	
	@Autowired
	SubjectResultsService service;
	
	
	@Autowired
	MessageSource messageSource;
	
	/*
	 * This method will list all existing subjectResultss.
	 */
	@RequestMapping(value = { "/", "/listSubjectResultss" }, method = RequestMethod.GET)
	public String listSubjectResultss(ModelMap model) {

		List<SubjectResults> subjectResultss = service.findAllSubjectResults();
		model.addAttribute("subjectResultss", subjectResultss);
		return "allsubjectResultss";
	}
	
	/*
	 * This method will provide the medium to add a new subjectResults.
	 */
	@RequestMapping(value = { "/newSubjectResults" }, method = RequestMethod.GET)
	public String newSubjectResults(ModelMap model) {
		SubjectResults subjectResults = new SubjectResults();
		model.addAttribute("subjectResults", subjectResults);
		model.addAttribute("edit", false);
		return "subjectResultsRegistration";
	}
	
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving subjectResults in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newSubjectResults" }, method = RequestMethod.POST)
	public String saveSubjectResults(@Valid SubjectResults subjectResults, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "subjectResultsRegistration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [subjectResultsId] should be implementing custom @Unique annotation 
		 * and applying it on field [subjectResultsId] of Model class [SubjectResults].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isSubjectResultsIdUnique(subjectResults.getId(), subjectResults.getTest_id(), subjectResults.getStudent_id())){
			FieldError ssnError =new FieldError("subjectResults","subjectResultsId",messageSource.getMessage("non.unique.Id", new String[]{String.valueOf(subjectResults.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "subjectResultsRegistration";
		}
		
		service.saveSubjectResults(subjectResults);

		model.addAttribute("success", "SubjectResults " + subjectResults.getId() + " registered successfully");
		return "success";
	}
	

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{subjectResultsId}-subjectResults" }, method = RequestMethod.GET)
	public String editSubjectResults(@PathVariable int test_id, int student_id, ModelMap model) {
		SubjectResults subjectResults1 = service.findSubjectResults(test_id, student_id);
		model.addAttribute("subjectResults", subjectResults1);
		model.addAttribute("edit", true);
		return "subjectResultsRegistration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{subjectResultsId}-subjectResults" }, method = RequestMethod.POST)
	public String updateSubjectResults(@Valid SubjectResults subjectResults, BindingResult result,
			ModelMap model, @PathVariable String subjectResultsId) {

		if (result.hasErrors()) {
			return "subjectResultsRegistration";
		}

		if(!service.isSubjectResultsIdUnique(subjectResults.getId(), subjectResults.getTest_id(), subjectResults.getStudent_id())){
			FieldError ssnError =new FieldError("subjectResults","subjectResultsId",messageSource.getMessage("non.unique.Id", new String[]{String.valueOf(subjectResults.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "subjectResultsRegistration";
		}

		service.updateSubjectResults(subjectResults);

		model.addAttribute("success", "SubjectResults " + subjectResults.getId()	+ " updated successfully");
		return "success";
	}
	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{subjectResultsId}-subjectResults" }, method = RequestMethod.GET)
	public String deleteSubjectResults(@PathVariable int id) {
		service.deleteSubjectResultsById(id);
		return "redirect:/listSubjectResults";
	}
}
