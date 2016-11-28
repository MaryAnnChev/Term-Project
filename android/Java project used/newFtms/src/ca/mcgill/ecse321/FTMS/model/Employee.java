/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// line 19 "../../../../../FTMS.ump"
// line 130 "../../../../../FTMS.ump"
public class Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private String staffName;
  private String staffRoles;
  private final List<DayOfTheWeek> schedule; // schedule.get(0) is monday and .get(6) is sunday
  

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aStaffName, String aStaffRoles)
  {
    staffName = aStaffName;
    staffRoles = aStaffRoles;
    schedule = new ArrayList<DayOfTheWeek>();
    
    // Initiating schedule.
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, 8);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    DayOfTheWeek monday = new DayOfTheWeek("Monday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(monday);
    DayOfTheWeek tuesday = new DayOfTheWeek("Tuesday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(tuesday);
    DayOfTheWeek wednesday = new DayOfTheWeek("Wednesday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(wednesday);
    DayOfTheWeek thursday = new DayOfTheWeek("Thursday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(thursday);
    DayOfTheWeek friday = new DayOfTheWeek("Friday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(friday);
    DayOfTheWeek saturday = new DayOfTheWeek("Saturday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(saturday);
    DayOfTheWeek sunday = new DayOfTheWeek("Sunday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(sunday);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStaffName(String aStaffName)
  {
    boolean wasSet = false;
    staffName = aStaffName;
    wasSet = true;
    return wasSet;
  }

  public boolean setStaffRoles(String aStaffRoles)
  {
    boolean wasSet = false;
    staffRoles = aStaffRoles;
    wasSet = true;
    return wasSet;
  }

  public void setMonday(Time start, Time end)
  {
    schedule.get(0).setStartTime(start);
    schedule.get(0).setEndTime(end);
  }
  
  public void setTuesday(Time start, Time end)
  {
    schedule.get(1).setStartTime(start);
    schedule.get(1).setEndTime(end);
  }
  
  public void setWednesday(Time start, Time end)
  {
    schedule.get(2).setStartTime(start);
    schedule.get(2).setEndTime(end);
  }
  
  public void setThursday(Time start, Time end)
  {
    schedule.get(3).setStartTime(start);
    schedule.get(3).setEndTime(end);
  }
  
  public void setFriday(Time start, Time end)
  {
    schedule.get(4).setStartTime(start);
    schedule.get(4).setEndTime(end);
  }
  
  public void setSaturday(Time start, Time end)
  {
    schedule.get(5).setStartTime(start);
    schedule.get(5).setEndTime(end);
  }
  
  public void setSunday(Time start, Time end)
  {
    schedule.get(6).setStartTime(start);
    schedule.get(6).setEndTime(end);
  }
  
  public Time getMondayStartTime() {
	  return this.schedule.get(0).getStartTime();
  }
  
  public Time getMondayEndTime() {
	  return this.schedule.get(0).getEndTime();
  }
  
  public Time getTuesdayStartTime() {
	  return this.schedule.get(1).getStartTime();
  }
  
  public Time getTuesdayEndTime() {
	  return this.schedule.get(1).getEndTime();
  }
  
  public Time getWednesdayStartTime() {
	  return this.schedule.get(2).getStartTime();
  }
  
  public Time getWednesdayEndTime() {
	  return this.schedule.get(2).getEndTime();
  }
  
  public Time getThursdayStartTime() {
	  return this.schedule.get(3).getStartTime();
  }
  
  public Time getThursdayEndTime() {
	  return this.schedule.get(3).getEndTime();
  }
  
  public Time getFridayStartTime() {
	  return this.schedule.get(4).getStartTime();
  }
  
  public Time getFridayEndTime() {
	  return this.schedule.get(4).getEndTime();
  }
  
  public Time getSaturdayStartTime() {
	  return this.schedule.get(5).getStartTime();
  }
  
  public Time getSaturdayEndTime() {
	  return this.schedule.get(5).getEndTime();
  }
  
  public Time getSundayStartTime() {
	  return this.schedule.get(6).getStartTime();
  }
  
  public Time getSundayEndTime() {
	  return this.schedule.get(6).getEndTime();
  }

  public String getStaffName()
  {
    return staffName;
  }

  public String getStaffRoles()
  {
    return staffRoles;
  }

  /*public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "staffName" + ":" + getStaffName()+ "," +
            "staffRoles" + ":" + getStaffRoles()+ "," +
            "hours" + ":" + getHours()+ "]"
     + outputString;
  }*/
}