package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;

public class ParsePIXY implements Runnable{

	I2C pixy;
	boolean isFinished = false;
	int pos = 0;
	int state = 0;
	double[] x = {0, 0};
	double[] y = {0, 0};
	int XYPos = 0;
	byte[] buffer2 = new byte[2];
	byte[] buffer10 = new byte[10];
	byte[] buffer1 = new byte[1];
	
	Thread t = new Thread(this);
	
	public ParsePIXY(I2C pixy) {
		this.pixy = pixy;
	}
	
	public void start(){
		if(!t.isAlive())t.start();
		isFinished = false;
	}

	double old = 0;
	@Override
	public void run() {
		while(!isFinished){
			switch(state){
			//synchronize
			case 0:
				buffer1();
				
				if((buffer1[0] == 85 && pos%2 == 0) || (buffer1[0] == -86 && pos%2 == 1)){
					pos++;
				} else {
					pos = 0;
				}
				if(pos == 4){
					state = 1;
					pos = 0;
					XYPos = 0;
				}
				break;
			//set checksum
			case 1:
				buffer2();
				state = 2;
				break;
			//compare checksum to sum
			case 2:
				buffer10();
				state = 3;
				break;
			//get x and y center
			case 3:
				if(XYPos > 1)XYPos = 0;
				if(XYPos == 0){
					x[1] = 0;
					y[1] = 0;
				}
				x[XYPos] = 0;
				x[XYPos] = toWord(buffer10[3], buffer10[2]);
				y[XYPos] = 0;
				y[XYPos] = toWord(buffer10[5], buffer10[4]);
				XYPos++;
				state = 4;
				break;
			//check for new object
			case 4:
				buffer2();
				if(buffer2[0] == 85 && buffer2[1] == -86)state = 5;
				else {
					state = 0;
					XYPos = 0;
				}
				break;
			//check for new frame
			case 5:
				buffer2();
				state = 2;
				if(buffer2[0] == 85 && buffer2[1] == -86){
					state = 1;
					XYPos = 0;
				}
				break;
			default:
				state = 0;
				break;
			}
			
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				System.out.println("failed to sleep");
			}
		}
	}
	
	public void buffer1(){
		pixy.readOnly(buffer1, 1);
	}
	
	public void buffer2(){
		pixy.readOnly(buffer2, 2);
	}
	
	public void buffer10(){
		pixy.readOnly(buffer10, 10);
	}
	
	public int toWord(byte one, byte two){
		return ((one << 8) | two)/* & 0x0000ffff*/;
	}
	
	public double[] getXs(){
		return x;
	}
	
	public double[] getYs(){
		return y;
	}
	
	public void stop(){
		isFinished = true;
	}

}
