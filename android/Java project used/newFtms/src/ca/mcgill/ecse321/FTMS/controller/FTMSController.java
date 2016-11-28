package ca.mcgill.ecse321.FTMS.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
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
		if (staffName.equals(""))
			error= error +"Employee name cannot be empty!";
		if (staffRoles.equals(""))
			error= error + "Employee roles cannot be empty!";
		if (hours==0)
			error= error +"hours to work cannot be 0";
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

		if (equipmentQty<0)
			error=error+"Equipment quantity cannot be negative!";
		if (equipmentName.equals(""))
			error += "Enter a name for the equipment! ";
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
		
		if (foodQty<0)
			error = error +"Food quantity cannot be negative!";
		if (foodName.equals(""))
			error += "Enter a name for the supply! ";
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		Supply s = new Supply(foodName, foodQty);
		
		om.addFoodSupply(s);
		
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
	}
	
	public void createMenu(String menuName, List<Supply> ingredients) throws InvalidInputException
	{
		
		OrderManager om = OrderManager.getInstance();
		String error = "";
		if (menuName.equals(""))
			error += "Enter a name for the dish!. ";
		else if (om.getMenu(menuName) != null)
			error += "This dish name already exists! ";
		else if (ingredients.size() == 0)
			error = error + "Ingredient needs to be selected!";
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		Menu mr = new Menu(menuName, ingredients, 0);
		om.addMenus(mr);
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
				
	}
	
	public void placeOrder(String menuName) throws Exception {
		OrderManager om = OrderManager.getInstance();
		Menu menu = om.getMenu(menuName);
		String error = "";
		if (menu.getMealsLeft() == 0)
			error += "You are missing one of the supplies shown";
		if (error.length() > 0)
			throw new Exception(error);
		
		menu.setPopularity(menu.getPopularity()+1);
		for(int i=0; i<menu.getIngredients().size(); i++)
			menu.getIngredients().get(i).setFoodQty(menu.getIngredients().get(i).getFoodQty()-1);
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
	}
	
	
}
