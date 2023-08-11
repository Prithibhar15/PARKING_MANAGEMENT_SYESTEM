package PARKING;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Helper {
public static SessionFactory factory;
	
	//singleton sessionfactory object
	 public static synchronized SessionFactory getSessionFactory() {

	        if (factory == null) {
	            factory = new Configuration().configure("hibernate.cfg.xml").
	                    buildSessionFactory();
	        }
	        return factory;
	    }
	 
	 //return a particular session object for a session factory
//now a days sessionfactory object contains 20 session object in the pool by default
	 public static Session getSession()
	 {
		 return getSessionFactory().openSession();
	 }

	public static void executeSQL(String string) {
		// TODO Auto-generated method stub
		
	}
}