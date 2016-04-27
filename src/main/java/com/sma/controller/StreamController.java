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

import com.sma.model.Stream;
import com.sma.service.StreamService;

@Controller
@RequestMapping("/stream")
public class StreamController {
	
	@Autowired
	StreamService service;
	
	
	@Autowired
	MessageSource messageSource;
	
	/*
	 * This method will list all existing streams.
	 */
	@RequestMapping(value = { "/", "/listStreams" }, method = RequestMethod.GET)
	public String listStreams(ModelMap model) {

		List<Stream> streams = service.findAllStreams();
		model.addAttribute("streams", streams);
		return "allstreams";
	}
	
	/*
	 * This method will provide the medium to add a new stream.
	 */
	@RequestMapping(value = { "/newStream" }, method = RequestMethod.GET)
	public String newStream(ModelMap model) {
		Stream stream = new Stream();
		model.addAttribute("stream", stream);
		model.addAttribute("edit", false);
		return "streamRegistration";
	}
	
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving stream in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newStream" }, method = RequestMethod.POST)
	public String saveStream(@Valid Stream stream, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "streamRegistration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [streamId] should be implementing custom @Unique annotation 
		 * and applying it on field [streamId] of Model class [Stream].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isStreamUnique(stream.getId(), stream.getStream(), stream.getYear())){
			FieldError ssnError =new FieldError("stream","streamId",messageSource.getMessage("non.unique.Id", new String[]{String.valueOf(stream.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "streamRegistration";
		}
		
		service.saveStream(stream);

		model.addAttribute("success", "Stream " + stream.getStream() + " registered successfully");
		return "success";
	}
	

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{streamId}-stream" }, method = RequestMethod.GET)
	public String editStream(@PathVariable String stream, LocalDate year, ModelMap model) {
		Stream stream1 = service.findStream(stream, year);
		model.addAttribute("stream", stream1);
		model.addAttribute("edit", true);
		return "streamRegistration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{streamId}-stream" }, method = RequestMethod.POST)
	public String updateStream(@Valid Stream stream, BindingResult result,
			ModelMap model, @PathVariable String streamId) {

		if (result.hasErrors()) {
			return "streamRegistration";
		}

		if(!service.isStreamUnique(stream.getId(), stream.getStream(), stream.getYear())){
			FieldError ssnError =new FieldError("stream","streamId",messageSource.getMessage("non.unique.Id", new String[]{String.valueOf(stream.getId())}, Locale.getDefault()));
		    result.addError(ssnError);
			return "streamRegistration";
		}

		service.updateStream(stream);

		model.addAttribute("success", "Stream " + stream.getStream()	+ " updated successfully");
		return "success";
	}
	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{streamId}-stream" }, method = RequestMethod.GET)
	public String deleteStream(@PathVariable int id) {
		service.deleteStream(id);
		return "redirect:/listStreams";
	}

}
