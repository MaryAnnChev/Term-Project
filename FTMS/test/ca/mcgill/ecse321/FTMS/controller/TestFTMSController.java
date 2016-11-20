package ca.mcgill.ecse321.FTMS.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStream;

public class TestFTMSController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			PersistenceXStream.setFilename("test"+File.separator + "ca"+File.separator+
					"mcgill"+File.separator+"ecse321"+File.separator+"FTMS"+
					File.separator+"controller"+File.separator+"FTMSStaffData.xml");
			PersistenceXStream.setAlias("schedule", Schedule.class);
			PersistenceXStream.setAlias("employee", Employee.class);
			PersistenceXStream.setAlias("registration", ScheduleRegistration.class);
			PersistenceXStream.setAlias("manager", StaffManager.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//clear all registrations
		StaffManager sm = StaffManager.getInstance();
		sm.delete();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		//clear all registrations
		StaffManager sm = StaffManager.getInstance();
		sm.delete();
	}

	@Test
	public void testCreateEmployee() {
			StaffManager sm = StaffManager.getInstance();
			assertEquals(0, sm.getEmployees().size());
			
			String name = "Ben";
			String role = "Chef";
			int hours = 56;
			FTMSController erc = new FTMSController();
			try {
				erc.createEmployee(name, role, hours);
			} catch (InvalidInputException e) {
				// check that no error occurred
				fail();
			}
			
			checkResultEmployee(name,role,hours, sm);
			
			StaffManager sm2 = (StaffManager) PersistenceXStream.loadFromXMLwithXStream();
			
			//check file content
			checkResultEmployee(name,role,hours, sm2);
	}
	
	@Test
	public void testCreateEmployeeNull(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getEmployees().size());
		
		String name = null;
		String role = null;
		int hours = 0;
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.createEmployee(name,role,hours);
		} catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, sm.getEmployees().size());
		
	
	}
	
	@Test
	public void testCreateEmployeeEmpty(){
		
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getEmployees().size());
		
		String staffName = null;
		String staffRoles =null;
		int hours = 0;
		
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.createEmployee(staffName, staffRoles, hours);
		} catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, sm.getEmployees().size());

	}
	
	@Test
	public void testCreateEmployeeSpaces(){
		
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getEmployees().size());
		
		String staffName = " ";
		String staffRoles = " ";
		int hours = 0;
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.createEmployee(staffName, staffRoles, hours);
		} catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty!", error);

		//check no change in memory
		assertEquals(0, sm.getEmployees().size());
	}
	
	@Test
	public void testCreateSchedule(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getSchedules().size());
		
		String nameS = "Monday";

		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		FTMSController erc = new FTMSController();
		try{
			erc.createSchedule(nameS, date, startTime, endTime);
		}catch (InvalidInputException e){
			//check that no error occurred
			fail();
		}
		//check model in memory
		checkResultSchedule(nameS, date, startTime, endTime, sm);
		StaffManager sm2 = (StaffManager) PersistenceXStream.loadFromXMLwithXStream();
		
		// check file contents
		checkResultSchedule(nameS, date, startTime, endTime, sm2);
		
	}
	@Test
	public void testCreateScheduleNull(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getSchedules().size());
		String weekday =null;
		Date date = null;
		Time startTime = null;
		Time endTime = null;
		
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.createSchedule(weekday, date, startTime, endTime);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		// check error
		assertEquals("Weekday cannot be empty!Schedule date cannot be empty!Schedule start time cannot be empty!Schedule end time cannot be empty!", error); 
		//check model in memory
		assertEquals(0, sm.getSchedules().size());
		
	}

	@Test
	public void testCreateScheduleEmpty(){
	StaffManager sm = StaffManager.getInstance();
	assertEquals(0, sm.getSchedules().size());
	
	String weekday = "";
	Calendar c = Calendar.getInstance();
	c.set(2016,Calendar.OCTOBER,16,9,00,0);
	Date eventDate = new Date(c.getTimeInMillis());
	Time startTime = new Time(c.getTimeInMillis());
	c.set(2016,Calendar.OCTOBER,16,10,30,0);
	Time endTime = new Time(c.getTimeInMillis());
	
	String error = null;
	FTMSController erc = new FTMSController();
	try{
		erc.createSchedule(weekday, eventDate, startTime, endTime);
	}catch (InvalidInputException e){
		error = e.getMessage();
	}
	//check error
	assertEquals("Weekday cannot be empty!", error);
	
	//check model in memory
	assertEquals(0, sm.getSchedules().size());	
	}
	
	@Test
	public void testCreateScheduleSpaces(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getSchedules().size());
		
		String weekday = "";
		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.createSchedule(weekday, date, startTime, endTime);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		//check error
		assertEquals("Weekday cannot be empty!", error);
		
		//check model in memory
		assertEquals(0, sm.getSchedules().size());	
			
	}
	
	@Test
	public void testCreateScheduleEndTimeBeforeStartTime(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getSchedules().size());
		String nameS = "Monday";

		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,8,59,59);
		Time endTime = new Time(c.getTimeInMillis());
		
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.createSchedule(nameS, date, startTime, endTime);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		//check error
		assertEquals("Schedule end time cannot be before Schedule start time!", error);
		
		//check model in memory
		assertEquals(0, sm.getSchedules().size());	
	}
	
	@Test
	public void testRegister(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getScheduleRegistrations().size());
		
		String nameE = "Ben";
		String roleE = "Chef";
		int hours=56;
		Employee Employee = new Employee(nameE,roleE, hours );
		sm.addEmployee(Employee);
		assertEquals(1, sm.getEmployees().size());
		String nameS = "Monday";

		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Schedule Schedule = new Schedule(nameS, date, startTime, endTime);
		sm.addSchedule(Schedule);
		assertEquals(1, sm.getSchedules().size());
		
		FTMSController erc = new FTMSController();
		try{
			erc.scheduleRegister(Employee, Schedule);
		}catch (InvalidInputException e){
			//check that no error occurred
			fail();
		}
		//check model in memory
		checkResultScheduleRegister(nameE,roleE, hours,nameS,  date, startTime, endTime, sm);
		
		StaffManager sm2 = (StaffManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultScheduleRegister(nameE, roleE,hours,nameS, date, startTime, endTime, sm2);
		
		}
	
	@Test
	public void testRegisterNull(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getScheduleRegistrations().size());
		
		Employee Employee = null;
		assertEquals(0, sm.getEmployees().size());
		
		Schedule Schedule = null;
		assertEquals(0, sm.getSchedules().size());
		
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.scheduleRegister(Employee, Schedule);
		}catch (InvalidInputException e){
			error= e.getMessage();
		}
		// check error
		assertEquals("Employee needs to be selected for registration! Schedule needs to be selected for registration!", error);
		
		//check model in memory
		assertEquals(0, sm.getScheduleRegistrations().size());
		assertEquals(0, sm.getEmployees().size());
		assertEquals(0, sm.getSchedules().size());
	}
	
	@Test
	public void testRegisterEmployeeAndScheduleDoNotExist(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getScheduleRegistrations().size());
		
		String nameE = "Ben";
		String roleE = "Chef";
		int hours = 56;
		
		Employee Employee = new Employee(nameE, roleE, hours);
		assertEquals(0, sm.getEmployees().size());
		String nameS = "Monday";
		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Schedule Schedule = new Schedule(nameS, date, startTime, endTime);
		assertEquals(0, sm.getSchedules().size());
		
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.scheduleRegister(Employee, Schedule);
		}catch (InvalidInputException e){
			error= e.getMessage();
		}	
		// check error
		assertEquals("Employee does not exist! Schedule does not exist!", error);
				
		//check model in memory
		assertEquals(0, sm.getScheduleRegistrations().size());
		assertEquals(0, sm.getEmployees().size());
		assertEquals(0, sm.getSchedules().size());
	}
			
	


	private void checkResultEmployee(String staffName,String staffRoles, int hours,  StaffManager sm2) {
		assertEquals(1, sm2.getEmployees().size());
		assertEquals(staffName, sm2.getEmployee(0).getStaffName());
		assertEquals(staffRoles, sm2.getEmployee(0).getStaffRoles());
		assertEquals(hours, sm2.getEmployee(0).getHours());
		assertEquals(0, sm2.getSchedules().size());
		assertEquals(0, sm2.getScheduleRegistrations().size());
	}

	private void checkResultSchedule(String Weekday, Date date, Time startTime, Time endTime, StaffManager sm2){
		assertEquals(0, sm2.getEmployees().size());
		assertEquals(1, sm2.getSchedules().size());
		assertEquals(date.toString(), sm2.getSchedule(0).getDate().toString());
		assertEquals(startTime.toString(), sm2.getSchedule(0).getStartTime().toString());
		assertEquals(endTime.toString(), sm2.getSchedule(0).getEndTime().toString());
		assertEquals(0, sm2.getScheduleRegistrations().size());
	}
	
	private void checkResultScheduleRegister(String nameE,String roleE, int hours, String nameS,  Date date, Time startTime, Time endTime, StaffManager sm2){
		assertEquals(1, sm2.getEmployees().size());
		assertEquals(nameE, sm2.getEmployee(0).getStaffName());
		assertEquals(roleE, sm2.getEmployee(0).getStaffRoles());
		assertEquals(hours, sm2.getEmployee(0).getHours());
		assertEquals(1, sm2.getSchedules().size());
		assertEquals(date.toString(), sm2.getSchedule(0).getDate().toString());
		assertEquals(startTime.toString(), sm2.getSchedule(0).getStartTime().toString());
		assertEquals(endTime.toString(), sm2.getSchedule(0).getEndTime().toString());
		assertEquals(1, sm2.getScheduleRegistrations().size());
		assertEquals(nameS, sm2.getSchedule(0).getWeekday());
		assertEquals(sm2.getSchedule(0), sm2.getScheduleRegistration(0).getSchedule());
		assertEquals(sm2.getEmployee(0), sm2.getScheduleRegistration(0).getEmployee());
		
	}
	
}


