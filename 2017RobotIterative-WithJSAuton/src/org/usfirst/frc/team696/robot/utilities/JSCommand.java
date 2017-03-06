package org.usfirst.frc.team696.robot.utilities;

import edu.wpi.first.wpilibj.Timer;

public class JSCommand implements Runnable{
	public static boolean isFinished = false;
	static Thread t;
	public static boolean inParallel = false;
	
	public JSCommand() {
		if(t == null)t = new Thread(this);
		inParallel = false;
	}
	
	public void start(){
		t.start();
	}
	
	public JSCommand(boolean inParallel) {
		this.inParallel = inParallel;
		if(t == null)t = new Thread(this);
		t.start();
	}
	
	public void run(){
		while(!isFinished){
//			if(timer.get() > 5) isFinished = true;
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
