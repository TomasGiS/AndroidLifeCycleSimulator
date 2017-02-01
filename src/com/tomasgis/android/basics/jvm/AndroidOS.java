package com.tomasgis.android.basics.jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.tomasgis.android.basics.Activity;
import com.tomasgis.android.basics.lifecycle.HelloActivity;

public class AndroidOS {
	//The mainActivity name is loaded from manifest
	static String mainActivityName = HelloActivity.class.getName();
	static Map<String,Activity> applicationsMap = new HashMap<>();
	
	public static void launchMainActivity() {
		try {
			
			Class<?> classActivity=  Class.forName(mainActivityName);
			Constructor<?> classConstructor = classActivity.getConstructor(new Class[]{});
			Activity activity=(Activity) classConstructor.newInstance();
			applicationsMap.put(mainActivityName, activity);
			activity.start();
			
		} catch (ClassNotFoundException e) {
			System.out.println("The class does not exist");
		} catch (InstantiationException e) {
			System.out.println("The class cannot  be instantiated");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Error calling class method");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void pauseMainActivity() {
		Activity activity = applicationsMap.get(mainActivityName);
		if (activity != null)
		{
			activity.pause();
		}
		else
		{
			System.out.println("Illegal state: "+mainActivityName);
		}
		
	}
	
	public static void stopMainActivity()
	{
		Activity activity = applicationsMap.get(mainActivityName);
		if (activity != null)
		{
			activity.stop();
		}
		else
		{
			System.out.println("Illegal state: "+mainActivityName);
		}
	}
	
	public static void destroyActivity()
	{
		Activity activity = applicationsMap.get(mainActivityName);
		if (activity != null)
		{
			applicationsMap.remove(mainActivityName);
			activity=null;
			System.gc();
		}
		else
		{
			System.out.println("Illegal state: "+mainActivityName);
		}
	}

	
	
}
