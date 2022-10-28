package com.barishal.view;

public class DashboardViewController {
	
	private static DashboardViewController dashboardViewController;
	
	
	private DashboardViewController() {
		
	}
	
	public static DashboardViewController getDashboardViewController() {
		
		if (dashboardViewController == null) { 
			dashboardViewController = new DashboardViewController();
		}
		return dashboardViewController;
	}
	
}
