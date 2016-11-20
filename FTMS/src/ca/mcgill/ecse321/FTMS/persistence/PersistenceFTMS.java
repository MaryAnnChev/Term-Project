package ca.mcgill.ecse321.FTMS.persistence;

import java.util.Iterator;


import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;



public class PersistenceFTMS {	
	private static String filename = "FTMSStaffData.xml";

	public static void setFilename(String fn) {
		filename = fn;
	}
	
	private static void initializeXStream(){
		PersistenceXStream.setFilename(filename +"ftms.xml");
		PersistenceXStream.setAlias("schedule", Schedule.class);
		PersistenceXStream.setAlias("employee", Employee.class);
		PersistenceXStream.setAlias("scheduleRegistration", ScheduleRegistration.class);
		PersistenceXStream.setAlias("manager", StaffManager.class);
		
	}
	
	public static void loadFTMSModel() {
		StaffManager sm = StaffManager.getInstance();
		PersistenceFTMS.initializeXStream();
		StaffManager sm2 = (StaffManager)PersistenceXStream.loadFromXMLwithXStream();
		if(sm2 != null){
			// unfortunately, this creates a second RegistrationManager object, even though its singleton
			//copy loaded model into singleton instance of RegistrationManager, because this will be used throughout the application
			Iterator<Employee> pIt = sm2.getEmployees().iterator();
			while(pIt.hasNext())
				sm.addEmployee(pIt.next());
			Iterator<Schedule> eIt = sm2.getSchedules().iterator();
			while(eIt.hasNext())
				sm.addSchedule(eIt.next());
			Iterator<ScheduleRegistration> rIt = sm2.getScheduleRegistrations().iterator();
			while(rIt.hasNext())
				sm.addScheduleRegistration(rIt.next());
		}
		
	}

}
