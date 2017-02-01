package com.tomasgis.android.basics;

public abstract class Activity implements IActivityLifeCycle{

	/** Shows if the activity is visible or hide*/
	private ActivityVisibility activityVisibility = ActivityVisibility.VISIBLE;
	
	/** Shows if the activity state */
	private ActivityState activityState = ActivityState.NOT_CREATED;
	


	
	public Activity()
	{
		this.activityState = ActivityState.NOT_CREATED;
		this.activityVisibility = ActivityVisibility.INVISIBLE;
	}
	
	@Override
	public void onCreate() {
		System.out.println("Called onCreate");
		this.activityState = ActivityState.CREATED;
		
		//Next step 
		onStart();
		
	}
	
	@Override
	public void onStart()
	{
		System.out.println("Called onStart");
		this.activityState = ActivityState.STARTED;
		this.activityVisibility = ActivityVisibility.INVISIBLE;
		
		//Next Step
		onResume();
	}

	@Override
	public void onRestart() {
		System.out.println("Called onRestart");
		this.activityState = ActivityState.STARTED;
		this.activityVisibility = ActivityVisibility.INVISIBLE;
		
		//Next Step
		onStart();
	}

	@Override
	public void onResume() {
		System.out.println("Called onResume");
		this.activityState = ActivityState.RESUMED;
		this.activityVisibility = ActivityVisibility.VISIBLE;
		
		//Next Step ==> nothing only running and waiting user interaction
		run();
	}

	@Override
	public void onPause() {
		System.out.println("Called onPause");
		this.activityState = ActivityState.PAUSED;
		this.activityVisibility = ActivityVisibility.INVISIBLE;
		
		//Next Step
		onStop();
	}

	@Override
	public void onStop() {
		System.out.println("Called onStop");
		this.activityState = ActivityState.STOPPED;
		this.activityVisibility = ActivityVisibility.INVISIBLE;
		
	}

	@Override
	public void onDestroy() {
		System.out.println("Called onDestroy");
		this.activityState = ActivityState.DESTROYED;
		this.activityVisibility = ActivityVisibility.INVISIBLE;
	}
	
	/**
	 * Java default method called before garbage collector is called
	 */
	@Override
	protected void finalize() throws Throwable {
		onDestroy();
		this.activityState = ActivityState.ZOMBIE;
		super.finalize();
		
	}
	@Override
	public void run() {
		this.activityState = ActivityState.RUNNING;
		IActivityLifeCycle.super.run();
	}
	
	// Activity states: NOT_CREATED,CREATED,STARTED,RESUMED,PAUSED,STOPPED,DESTROYED,ZOMBIE
	@Override
	public void start() {
		switch(this.activityState)
		{
			case NOT_CREATED:{onCreate();}break;
			case CREATED :{onStart();}break;
			case STARTED: {onRestart();}break;
			case RESUMED: {this.activityState = ActivityState.RUNNING;
						   this.activityVisibility = ActivityVisibility.VISIBLE;
						  }
			case PAUSED:  {onRestart();}break;
			case STOPPED: {onRestart();}break;
			case DESTROYED: {System.out.println("Nothing to do, the activity is in destroyed state");}break;
			case ZOMBIE: {System.out.println("Nothing to do, the activity is in zombie state");}break;
			case RUNNING:{System.out.println("Activity is actualy running");} break;
			default:{System.out.println("Illegal state: Do nothing");} break;
			
		}
		
	}
	
	@Override 
	public void pause()
	{
		switch(this.activityState)
		{
		case RUNNING:{onPause();}break;
		default:{System.out.println("Illegal state: Do nothing");}
		}
	}

	@Override
	public void stop() {
		switch(this.activityState)
		{
			case RUNNING: {onPause();} break;
			case PAUSED:{onStop();}break;
			case DESTROYED:{this.activityState=ActivityState.ZOMBIE;}break;
			case ZOMBIE:{System.out.println("Illegal state: Do nothing");} break;
			default:{System.out.println("Illegal state: Do nothing");} break;
			
		}
		
	}



}
