package ca.mcgill.ecse321.FTMS.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStream;

public class TestPersistence {

	@Before
	public void setUp() throws Exception {
		StaffManager fm = StaffManager.getInstance();
		
		//create Staff
		Employee em = new Employee("Ben","Chef",56);
		Employee em2 = new Employee("Roman","Cashier", 56);	
		
		//create schedule
		
		Calendar c = Calendar.getInstance();
		c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
		Time endTime = new Time(c.getTimeInMillis());
		Schedule sc = new Schedule("monday", date, startTime, endTime);
		
		//register participants to event
		ScheduleRegistration sr = new ScheduleRegistration(em, sc);
		ScheduleRegistration sr2 = new ScheduleRegistration(em2, sc);
		
		//manage registrations
		fm.addScheduleRegistration(sr);
		fm.addScheduleRegistration(sr2);
		fm.addSchedule(sc);
		fm.addEmployee(em);
		fm.addEmployee(em2);
		

	}

	@After
	public void tearDown() throws Exception {
		//clear all Schedule registrations
				StaffManager sm = StaffManager.getInstance();
				sm.delete();
	}

	@Test
	public void test() {
		//save model
				StaffManager sm = StaffManager.getInstance();
				PersistenceXStream.setFilename("test"+File.separator + "ca"+File.separator+
						"mcgill"+File.separator+"ecse321"+File.separator+"FTMS"+
						File.separator+"persistence"+File.separator+"FTMSStaffData.xml");
				PersistenceXStream.setAlias("schedule", Schedule.class);
				PersistenceXStream.setAlias("employee", Employee.class);
				PersistenceXStream.setAlias("scheduleRegistration", ScheduleRegistration.class);
				PersistenceXStream.setAlias("manager", StaffManager.class);
				if (!PersistenceXStream.saveToXMLwithXStream(sm))
				fail("Could not save file.");
				
				//clear the model in memory
				sm.delete();
				assertEquals(0, sm.getEmployees().size());
				assertEquals(0, sm.getSchedules().size());
				assertEquals(0, sm.getScheduleRegistrations().size());
				
				//load model
				sm = (StaffManager) PersistenceXStream.loadFromXMLwithXStream();
				if (sm == null)
					fail("could not load file.");
				
				//check employees
				assertEquals(2, sm.getEmployees().size());
				assertEquals("Ben", sm.getEmployee(0).getStaffName().toString());
				assertEquals("Chef", sm.getEmployee(0).getStaffRoles().toString());
				assertEquals(56,sm.getEmployee(0).getHours());
				assertEquals("Roman", sm.getEmployee(1).getStaffName().toString());
				assertEquals("Cashier", sm.getEmployee(1).getStaffRoles().toString());
				assertEquals(56,sm.getEmployee(1).getHours());
				
				//check schedule
				assertEquals(1, sm.getSchedules().size());
				assertEquals("monday", sm.getSchedule(0).getWeekday());
				assertEquals(1, sm.getSchedules().size());
				Calendar c = Calendar.getInstance();
				c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
				Date date = new Date(c.getTimeInMillis());
				Time startTime = new Time(c.getTimeInMillis());
				c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
				Time endTime = new Time(c.getTimeInMillis());
				assertEquals(date.toString(), sm.getSchedule(0).getDate().toString());
				assertEquals(startTime.toString(), sm.getSchedule(0).getStartTime().toString());
				assertEquals(endTime.toString(), sm.getSchedule(0).getEndTime().toString());
				
				//check schedule registrations
				assertEquals(2, sm.getScheduleRegistrations().size());
				assertEquals(sm.getSchedule(0), sm.getScheduleRegistration(0).getSchedule());
				assertEquals(sm.getEmployee(0), sm.getScheduleRegistration(0).getEmployee());
				assertEquals(sm.getSchedule(0), sm.getScheduleRegistration(1).getSchedule());
				assertEquals(sm.getEmployee(1), sm.getScheduleRegistration(1).getEmployee());
				
				
	}

}
