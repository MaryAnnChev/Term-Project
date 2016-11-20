package ca.mcgill.ecse321.FTMS.controller;

import java.sql.Date;

import java.sql.Time;

import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStream;

public class FTMSController {

	public FTMSController()
	{
	}
	
	public void createEmployee(String staffName, String staffRoles, int hours)throws InvalidInputException
	{		
		if (staffName==null|| staffName.trim().length() == 0)
			throw new InvalidInputException("Employee name cannot be empty!");
		if (staffRoles==null|| staffRoles.trim().length() == 0)
			throw new InvalidInputException("Employee roles cannot be empty!");
		if (hours==0)
			throw new InvalidInputException("Employee hour cannot be empty!");
		
		
		Employee e = new Employee(staffName,staffRoles,hours);
		
		StaffManager sm = StaffManager.getInstance();
		sm.addEmployee(e);
		
		PersistenceXStream.saveToXMLwithXStream(sm);
	}
	
	public void createSchedule(String weekday, Date date, Time startTime, Time endTime) throws InvalidInputException
	{
		String error = "";
		if (weekday == null || weekday.trim().length() == 0 )
			error = error + "Weekday cannot be empty!";
		if (date == null)
			error = error + "Schedule date cannot be empty!";
		if (startTime == null)
			error = error + "Schedule start time cannot be empty!";
		if (endTime == null)
			error = error + "Schedule end time cannot be empty!";
		if(endTime != null && startTime != null && endTime.getTime()<startTime.getTime())
			error = error + "Schedule end time cannot be before Schedule start time!";
		error = error.trim();
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		Schedule s = new Schedule(weekday, date, startTime, endTime);
		StaffManager sm = StaffManager.getInstance();
		sm.addSchedule(s);
		PersistenceXStream.saveToXMLwithXStream(sm);
		
	}
	public void scheduleRegister(Employee employee, Schedule schedule) throws InvalidInputException
	{
		StaffManager sm = StaffManager.getInstance();
		
		String error = "";
		if (employee == null)
			error = error + "Employee needs to be selected for registration! ";
		else if (!sm.getEmployees().contains(employee))
			error = error + "Employee does not exist! ";
		if (schedule == null)
			error = error + "Schedule needs to be selected for registration!";
		else if (!sm.getSchedules().contains(schedule))
			error = error + "Schedule does not exist!";
		error = error.trim();
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		ScheduleRegistration sr = new ScheduleRegistration(employee, schedule);
		sm.addScheduleRegistration(sr);
		PersistenceXStream.saveToXMLwithXStream(sm);
		
	}

}
