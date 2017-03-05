package org.usfirst.frc.team696.robot.utilities;

public class JSCommand implements Runnable{
	public static boolean isFinished = false;
	static Thread t;
	public static boolean inParallel = false;
	
	public JSCommand() {
		if(t == null)t = new Thread(this);
		t.start();
		inParallel = false;
	}
	
	public JSCommand(boolean inParallel) {
		JSCommand.inParallel = inParallel;
		if(t == null)t = new Thread(this);
		t.start();
	}
	
	public void run(){
		while(!isFinished){
			update();
		}
	}

	public void update(){
		
	}
	
	public static boolean inParallel(){
		return inParallel;
	}
	
	public static boolean isFinished(){
		return isFinished;
	}
}
