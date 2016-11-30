package ca.mcgill.ecse321.FTMS.controller;

import java.sql.Date;

import java.sql.Time;
import java.util.Iterator;

import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.OrderTracker;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;
import ca.mcgill.ecse321.FTMS.model.Supply;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamOrder;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamSchedule;

public class FTMSController {

	public FTMSController()
	{
	}
	
	public void createEmployee(String staffName, String staffRoles, int hours)throws InvalidInputException
	{		String error = "";
		if (staffName==null|| staffName.trim().length() == 0)
			error= error +"Employee name cannot be empty!";
		if (staffRoles==null|| staffRoles.trim().length() == 0)
			error= error + "Employee roles cannot be empty!";
		if (hours==0)
			error= error +"hours to work cannot be 0";
		if (hours>40)
			error = error + "Hours must be lower than the 40 hours";
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		Employee e = new Employee(staffName,staffRoles,hours);
		StaffManager sm = StaffManager.getInstance();
		sm.addEmployee(e);
		PersistenceXStreamSchedule.saveToXMLwithXStream(sm);
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
		PersistenceXStreamSchedule.saveToXMLwithXStream(sm);
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
		PersistenceXStreamSchedule.saveToXMLwithXStream(sm);
		
	}

	public void createEquipment(String equipmentName,int equipmentQty)throws InvalidInputException
	{	
		OrderManager om = OrderManager.getInstance();

		String error="";
		if (equipmentQty==0)
			error=error+"Equipment quantity cannot be zero!";
		error = error.trim();
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		
		Equipment e = new Equipment(equipmentName, equipmentQty);
		om.addEquipment(e);
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
	}
	
	public void createSupply(String foodName,int foodQty)throws InvalidInputException
	{	
		OrderManager om = OrderManager.getInstance();
		
		String error="";
		if (foodName==null)
			error=error+"Food quantity has been updated";
		if (foodQty==0)
			error = error +"Food quantity cannot be zero!";
		error = error.trim();
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
			
		Supply s = new Supply(foodName, foodQty);
		om.addFoodSupply(s);
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
	}
	
	public void createMenu(String menuName, Supply ingredientName,int ingredientQty, double price) throws InvalidInputException
	{
		
			OrderManager om = OrderManager.getInstance();
			String error = "";
			if (menuName == null)
				error = error + "Menu needs to be selected for Menu registration! ";
			if (ingredientName == null)
				error = error + "Ingredient needs to be selected for Menu registration! ";
			if(ingredientQty==0)
				error=error+"Ingredient quantity must be higher than zero";
			
			error = error.trim();
			if (price<0)
				error=error+"Price must be positive";
				if (error.length()> 0)
				throw new InvalidInputException(error);
			
				Menu mr = new Menu(menuName, ingredientName,ingredientQty, price);
				om.addMenus(mr);
				PersistenceXStreamOrder.saveToXMLwithXStream(om);
				
	}
	
public void createTracker(String saleName, int saleQty) throws InvalidInputException
	{
		
			OrderManager om = OrderManager.getInstance();
			String error = "";
			
			if(saleQty==0)
				error=error+"Sale quantity must be higher than 0";
			error = error.trim();

			if (error.length()> 0)
				throw new InvalidInputException(error);
			
				OrderTracker sr = new OrderTracker(saleName,  saleQty);
				om.addTracker(sr);
				PersistenceXStreamOrder.saveToXMLwithXStream(om);
				
	}
	
	
}
