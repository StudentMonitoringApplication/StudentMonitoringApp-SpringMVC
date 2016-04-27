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

import com.sma.model.ResultsPerPaper;
import com.sma.service.ResultsPerPaperService;

@Controller
@RequestMapping("/ResultsPerPaper")
public class ResultsPerPaperController {

	@Autowired
	ResultsPerPaperService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 /* This method will list all existing Subjects.*/
	 
	@RequestMapping(value = { "/ResultsPerPaper", "/listResultsPerPaper" }, method = RequestMethod.GET)
	public String listHeadsOfDepartment(ModelMap model) {

		List<ResultsPerPaper> ResultsPerPaper = service.findAllResultsPerPapers();
		model.addAttribute("ResultsPerPaper", ResultsPerPaper);
		return "allResultsPerPaper";
	}
	
	
	  /*This method will provide the medium to add a new Subject.*/
	 
	@RequestMapping(value = { "/newResultsPerPaper" }, method = RequestMethod.GET)
	public String newResultsPerPaper(ModelMap model) {
		ResultsPerPaper ResultsPerPaper = new ResultsPerPaper();
		model.addAttribute("ResultsPerPaper", ResultsPerPaper);
		model.addAttribute("edit", false);
		return "ResultsPerPaperRegistration";
	}
	
	
	
	 /*This method will be called on form submission, handling POST request for
	 saving Subject in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/newResultsPerPaper" }, method = RequestMethod.POST)
	public String saveResultsPerPaper(@Valid ResultsPerPaper resultsPerPaper, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "ResultsPerPaperRegistration";
		}

		
		 /** Preferred way to achieve uniqueness of field [Code] should be implementing custom @Unique annotation 
		 * and applying it on field [Code] of Model class [Subject].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 **/ 
		 
		if(!service.isResultsPerPaperIdUnique(resultsPerPaper.getId(), resultsPerPaper.getTest_id(), resultsPerPaper.getStudent_id())){
			FieldError ssnError =new FieldError("ResultsPerPaper","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(resultsPerPaper.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "ResultsPerPaperRegistration";
		}
		
		service.saveResultsPerPaper(resultsPerPaper);

		model.addAttribute("success", "ResultsPerPaper " + resultsPerPaper.getId() + " registered successfully");
		return "success";
	}
	

	
	 /*This method will provide the medium to update an existing employee.*/
	 
	@RequestMapping(value = { "/edit-{test_id}-{student_id}-resultsPerPaper" }, method = RequestMethod.GET)
	public String editSubject(@PathVariable int test_id, @PathVariable int student_id, ModelMap model) {
		ResultsPerPaper resultsPerPaper = service.findResultsPerPaper(test_id, student_id);
		model.addAttribute("ResultsPerPaper", resultsPerPaper);
		model.addAttribute("edit", true);
		return "resultsPerPaperRegistration";
	}
	
	
	 /*This method will be called on form submission, handling POST request for
	 updating employee in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/edit-{test_id}-{student_id}-resultsPerPaper" }, method = RequestMethod.POST)
	public String updateSubject(@Valid ResultsPerPaper resultsPerPaper, BindingResult result,
			ModelMap model, @PathVariable int test_id, @PathVariable int student_id) {

		if (result.hasErrors()) {
			return "resultsPerPaperRegistration";
		}

		if(!service.isResultsPerPaperIdUnique(resultsPerPaper.getId(), resultsPerPaper.getTest_id(), resultsPerPaper.getStudent_id())){
			FieldError ssnError =new FieldError("ResultsPerPaper","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(resultsPerPaper.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "ResultsPerPaperRegistration";
		}

		service.updateResultsPerPaper(resultsPerPaper);

		model.addAttribute("success", "ResultsPerPaper " + resultsPerPaper.getId()	+ " updated successfully");
		return "success";
	}
	
	
	 /*This method will delete an employee by it's SSN value.*/	 
	@RequestMapping(value = { "/delete-{id}-resultsPerPaper" }, method = RequestMethod.GET)
	public String deleteSubject(@PathVariable int id) {
		service.deleteResultsPerPaperById(id);
		return "redirect:/listResultsPerPaper";
	}

}
