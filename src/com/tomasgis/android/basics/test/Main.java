package com.tomasgis.android.basics.test;

import com.tomasgis.android.basics.jvm.AndroidOS;

/**
 * Basic Android Android life cycle emulation
 * @author TomasGiS
 *
 */
public class Main {

	
	
	public static void main(String[] args) {
		
		AndroidOS.launchMainActivity();
		AndroidOS.pauseMainActivity();
		//Activity cannot be stopped because is already stopped
		AndroidOS.stopMainActivity();
		AndroidOS.destroyActivity();
		//Activity cannot be stopped because is already destroyed
		//AndroidOS.pauseMainActivity();
	}

}
