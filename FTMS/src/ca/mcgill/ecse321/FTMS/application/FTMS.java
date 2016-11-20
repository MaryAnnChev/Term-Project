package ca.mcgill.ecse321.FTMS.application;

import ca.mcgill.ecse321.FTMS.persistence.PersistenceFTMS;

import ca.mcgill.ecse321.FTMS.view.FTMSPage;

public class FTMS {
		public static void main(String[] args) {
			//load model
			PersistenceFTMS.loadFTMSModel();
			
			// start UI
			java.awt.EventQueue.invokeLater(new Runnable(){
				public void run(){
					new FTMSPage().setVisible(true);
				}
			});

		}

	}
