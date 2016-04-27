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

import com.sma.model.Parent;
import com.sma.service.ParentService;

@Controller
@RequestMapping("/parent")
public class ParentController {

	@Autowired
	ParentService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 /* This method will list all existing parents.*/
	 
	@RequestMapping(value = { "/parent", "/listParents" }, method = RequestMethod.GET)
	public String listParents(ModelMap model) {

		List<Parent> parents = service.findAllParents();
		model.addAttribute("parents", parents);
		return "allParents";
	}
	
	
	  /*This method will provide the medium to add a new parent.*/
	 
	@RequestMapping(value = { "/newParent" }, method = RequestMethod.GET)
	public String newParent(ModelMap model) {
		Parent parent = new Parent();
		model.addAttribute("parent", parent);
		model.addAttribute("edit", false);
		return "parentRegistration";
	}
	
	
	
	 /*This method will be called on form submission, handling POST request for
	 saving parent in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/newParent" }, method = RequestMethod.POST)
	public String saveparent(@Valid Parent parent, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "parentRegistration";
		}

		
		 /** Preferred way to achieve uniqueness of field [parentId] should be implementing custom @Unique annotation 
		 * and applying it on field [parentId] of Model class [parent].
		 * 
		 * Below mentioned peace of parentId [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 **/ 
		 
		if(!service.isParentIdUnique(parent.getId(), parent.getParentId())){
			FieldError ssnError =new FieldError("parent","parentId",messageSource.getMessage("non.unique.parentId", new String[]{parent.getParentId()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "parentRegistration";
		}
		
		service.saveParent(parent);

		model.addAttribute("success", "parent " + parent.getName() + " registered successfully");
		return "success";
	}
	

	
	 /*This method will provide the medium to update an existing employee.*/
	 
	@RequestMapping(value = { "/edit-{parentId}-parent" }, method = RequestMethod.GET)
	public String editparent(@PathVariable String parentId, ModelMap model) {
		Parent parent = service.findParentByParentId(parentId);
		model.addAttribute("parent", parent);
		model.addAttribute("edit", true);
		return "parentRegistration";
	}
	
	
	 /*This method will be called on form submission, handling POST request for
	 updating employee in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/edit-{parentId}-parent" }, method = RequestMethod.POST)
	public String updateParent(@Valid Parent parent, BindingResult result,
			ModelMap model, @PathVariable String parentId) {

		if (result.hasErrors()) {
			return "parentRegistration";
		}

		if(!service.isParentIdUnique(parent.getId(), parent.getParentId())){
			FieldError ssnError =new FieldError("parent","parentId",messageSource.getMessage("non.unique.parentId", new String[]{parent.getParentId()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "parentRegistration";
		}

		service.updateParent(parent);

		model.addAttribute("success", "parent " + parent.getName()	+ " updated successfully");
		return "success";
	}
	
	
	 /*This method will delete an employee by it's SSN value.*/	 
	@RequestMapping(value = { "/delete-{parentId}-parent" }, method = RequestMethod.GET)
	public String deleteParent(@PathVariable String parentId) {
		service.deleteParentByParentId(parentId);
		return "redirect:/listParents";
	}

}
