package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		// create a student object
		Student theStudent = new Student();

		// add student to object to the model
		theModel.addAttribute("student", theStudent);

		return "student-form";
	}

	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student theStudent) {

		// log the input data
		System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
		System.out.println("theStudent: " + theStudent.getFavouriteLanguage());

		return "student-confirmation";
	}
}
