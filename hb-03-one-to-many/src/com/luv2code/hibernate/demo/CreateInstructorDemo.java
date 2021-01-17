package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			// create the objects
			Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com", "Video Games");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			// IT will also save the details object because of the Cascade.All
			System.out.println("Savng Instructor: " + tempInstructor);
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
