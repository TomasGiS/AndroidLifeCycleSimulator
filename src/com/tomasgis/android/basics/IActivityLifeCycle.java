package com.tomasgis.android.basics;

/**
 * Basic Android Activity Life Cycle behaviour 
 * @author TomasGiS
 *
 */
public interface IActivityLifeCycle {
	/** First method called when an Activity is created*/
	public void onCreate();
	
	/** Called when Activity never been started before */
	public void onStart();
	
	/** This method is called after your activity has been stopped*/
	public void onRestart();
	
	/** Called when the activity will start interacting with the user*/
	public void onResume();
	
	/**	Called when the system is about to start resuming a previous activity*/
	public void onPause();
	
	/** Called when the activity is no longer visible to the user*/
	public void onStop();
	
	/** Called before the activity will be destroyed*/
	public void onDestroy();
	
	
	public void start();
	
	public void pause();
	
	public void stop();
	
	public default void run(){System.out.println("The Activity is running: "+this.getClass().getSimpleName());};
}
