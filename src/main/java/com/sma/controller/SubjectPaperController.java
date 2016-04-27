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

import com.sma.model.SubjectPaper;
import com.sma.service.SubjectPaperService;

@Controller
@RequestMapping("/subjectPaperPaper")
public class SubjectPaperController {
	
	@Autowired
	SubjectPaperService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 /* This method will list all existing SubjectPapers.*/
	 
	@RequestMapping(value = { "/subjectPaper", "/listSubjectPapers" }, method = RequestMethod.GET)
	public String listSubjectPapers(ModelMap model) {

		List<SubjectPaper> subjectPapers = service.findAllSubjectPapers();
		model.addAttribute("subjectPapers", subjectPapers);
		return "allSubjectPapers";
	}
	
	
	  /*This method will provide the medium to add a new SubjectPaper.*/
	 
	@RequestMapping(value = { "/newSubjectPaper" }, method = RequestMethod.GET)
	public String newSubjectPaper(ModelMap model) {
		SubjectPaper subjectPaper = new SubjectPaper();
		model.addAttribute("subjectPaper", subjectPaper);
		model.addAttribute("edit", false);
		return "subjectPaperRegistration";
	}
	
	
	
	 /*This method will be called on form submission, handling POST request for
	 saving SubjectPaper in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/newSubjectPaper" }, method = RequestMethod.POST)
	public String saveSubjectPaper(@Valid SubjectPaper subjectPaper, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "subjectPaperRegistration";
		}

		
		 /** Preferred way to achieve uniqueness of field [Code] should be implementing custom @Unique annotation 
		 * and applying it on field [Code] of Model class [SubjectPaper].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 **/ 
		 
		if(!service.isSubjectPaperUnique(subjectPaper.getId(), subjectPaper.getCode())){
			FieldError ssnError =new FieldError("subjectPaper","code",messageSource.getMessage("non.unique.code", new String[]{subjectPaper.getCode()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "subjectPaperRegistration";
		}
		
		service.saveSubjectPaper(subjectPaper);

		model.addAttribute("success", "subjectPaper " + subjectPaper.getName() + " registered successfully");
		return "success";
	}
	

	
	 /*This method will provide the medium to update an existing employee.*/
	 
	@RequestMapping(value = { "/edit-{code}-subjectPaper" }, method = RequestMethod.GET)
	public String editSubjectPaper(@PathVariable String code, ModelMap model) {
		SubjectPaper subjectPaper = service.findSubjectPaperByCode(code);
		model.addAttribute("subjectPaper", subjectPaper);
		model.addAttribute("edit", true);
		return "subjectPaperRegistration";
	}
	
	
	 /*This method will be called on form submission, handling POST request for
	 updating employee in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/edit-{code}-subjectPaper" }, method = RequestMethod.POST)
	public String updateSubjectPaper(@Valid SubjectPaper subjectPaper, BindingResult result,
			ModelMap model, @PathVariable String code) {

		if (result.hasErrors()) {
			return "subjectPaperRegistration";
		}

		if(!service.isSubjectPaperUnique(subjectPaper.getId(), subjectPaper.getCode())){
			FieldError ssnError =new FieldError("subjectPaper","code",messageSource.getMessage("non.unique.code", new String[]{subjectPaper.getCode()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "subjectPaperRegistration";
		}

		service.updateSubjectPaper(subjectPaper);

		model.addAttribute("success", "subjectPaper " + subjectPaper.getName()	+ " updated successfully");
		return "success";
	}
	
	
	 /*This method will delete an employee by it's SSN value.*/	 
	@RequestMapping(value = { "/delete-{code}-subjectPaper" }, method = RequestMethod.GET)
	public String deleteSubjectPaper(@PathVariable String code) {
		service.deleteSubjectPaperByCode(code);
		return "redirect:/listSubjectPapers";
	}
}
