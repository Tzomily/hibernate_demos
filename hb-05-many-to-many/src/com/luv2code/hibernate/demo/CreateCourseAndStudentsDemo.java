package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// create a course
			Course tempCourse = new Course("Pacman - How to score one million points!");

			// save the course
			System.out.println("\nSaving the course...");
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);

			// create Students
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student("Mary", "Public", "marypublic@luv2code.com");
			Student tempStudent3 = new Student("Chris", "Doe", "chris@luv2code.com");

			// add Students
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			tempCourse.addStudent(tempStudent3);

			// save the students
			System.out.println("\nSaving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			System.out.println("\nSaved the students: " + tempStudent1 + tempStudent2 + tempStudent3);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
