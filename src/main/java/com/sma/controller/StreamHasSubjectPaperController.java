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

import com.sma.model.StreamHasSubjectPaper;
import com.sma.service.StreamHasSubjectPaperService;

@Controller
@RequestMapping("/StreamHasSubjectPaper")
public class StreamHasSubjectPaperController {

	@Autowired
	StreamHasSubjectPaperService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 /* This method will list all existing Subjects.*/
	 
	@RequestMapping(value = { "/StreamHasSubjectPaper", "/listStreamHasSubjectPaper" }, method = RequestMethod.GET)
	public String listHeadsOfDepartment(ModelMap model) {

		List<StreamHasSubjectPaper> StreamHasSubjectPaper = service.findAllStreamHasSubjectPapers();
		model.addAttribute("StreamHasSubjectPaper", StreamHasSubjectPaper);
		return "allStreamHasSubjectPaper";
	}
	
	
	  /*This method will provide the medium to add a new Subject.*/
	 
	@RequestMapping(value = { "/newStreamHasSubjectPaper" }, method = RequestMethod.GET)
	public String newStreamHasSubjectPaper(ModelMap model) {
		StreamHasSubjectPaper StreamHasSubjectPaper = new StreamHasSubjectPaper();
		model.addAttribute("StreamHasSubjectPaper", StreamHasSubjectPaper);
		model.addAttribute("edit", false);
		return "StreamHasSubjectPaperRegistration";
	}
	
	
	
	 /*This method will be called on form submission, handling POST request for
	 saving Subject in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/newStreamHasSubjectPaper" }, method = RequestMethod.POST)
	public String saveStreamHasSubjectPaper(@Valid StreamHasSubjectPaper streamHasSubjectPaper, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "StreamHasSubjectPaperRegistration";
		}

		
		 /** Preferred way to achieve uniqueness of field [Code] should be implementing custom @Unique annotation 
		 * and applying it on field [Code] of Model class [Subject].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 **/ 
		 
		if(!service.isStreamHasSubjectPaperIdUnique(streamHasSubjectPaper.getId(), streamHasSubjectPaper.getStreamId(), streamHasSubjectPaper.getSubject_paper_id())){
			FieldError ssnError =new FieldError("StreamHasSubjectPaper","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(streamHasSubjectPaper.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "StreamHasSubjectPaperRegistration";
		}
		
		service.saveStreamHasSubjectPaper(streamHasSubjectPaper);

		model.addAttribute("success", "StreamHasSubjectPaper " + streamHasSubjectPaper.getId() + " registered successfully");
		return "success";
	}
	

	
	 /*This method will provide the medium to update an existing employee.*/
	 
	@RequestMapping(value = { "/edit-{streamId}-{subject_paper_id}-streamHasSubjectPaper" }, method = RequestMethod.GET)
	public String editSubject(@PathVariable int streamId, @PathVariable int subject_paper_id, ModelMap model) {
		StreamHasSubjectPaper streamHasSubjectPaper = service.findStreamHasSubjectPaper(streamId, subject_paper_id);
		model.addAttribute("StreamHasSubjectPaper", streamHasSubjectPaper);
		model.addAttribute("edit", true);
		return "streamHasSubjectPaperRegistration";
	}
	
	
	 /*This method will be called on form submission, handling POST request for
	 updating employee in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/edit-{streamId}-{subject_paper_id}-streamHasSubjectPaper" }, method = RequestMethod.POST)
	public String updateSubject(@Valid StreamHasSubjectPaper streamHasSubjectPaper, BindingResult result,
			ModelMap model, @PathVariable int streamId, @PathVariable int subject_paper_id) {

		if (result.hasErrors()) {
			return "streamHasSubjectPaperRegistration";
		}

		if(!service.isStreamHasSubjectPaperIdUnique(streamHasSubjectPaper.getId(), streamHasSubjectPaper.getStreamId(), streamHasSubjectPaper.getSubject_paper_id())){
			FieldError ssnError =new FieldError("StreamHasSubjectPaper","id",messageSource.getMessage("non.unique.id", new String[]{String.valueOf(streamHasSubjectPaper.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "StreamHasSubjectPaperRegistration";
		}

		service.updateStreamHasSubjectPaper(streamHasSubjectPaper);

		model.addAttribute("success", "StreamHasSubjectPaper " + streamHasSubjectPaper.getId()	+ " updated successfully");
		return "success";
	}
	
	
	 /*This method will delete an employee by it's SSN value.*/	 
	@RequestMapping(value = { "/delete-{id}-streamHasSubjectPaper" }, method = RequestMethod.GET)
	public String deleteSubject(@PathVariable int id) {
		service.deleteStreamHasSubjectPaperById(id);
		return "redirect:/listStreamHasSubjectPaper";
	}

}
