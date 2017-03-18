package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.I2C;

public class ParsePIXY implements Runnable{

	I2C pixy;
	boolean isFinished = false;
	int pos = 0;
	int state = 0;
	double[] x = {0, 0};
	double[] y = {0, 0};
	int XYPos = 0;
	int checkSum = 0;
	int sum = 0;
	byte[] buffer2 = new byte[2];
	byte[] buffer10 = new byte[10];
	byte[] buffer1 = new byte[1];
	
	Thread t = new Thread(this);
	
	public ParsePIXY(I2C pixy) {
		this.pixy = pixy;
	}
	
	public void start(){
		t.start();
		isFinished = false;
	}

	@Override
	public void run() {
		while(!isFinished){
//			if(pos == 4){
//				pos = 0;
//				pixy.readOnly(longBuffer, bufferLength);
//				for(int i = 0; i < longBuffer.length; i++){
//					System.out.print(longBuffer[i] + "   ");
//				}
//				System.out.println();
//			} else {
//				pixy.readOnly(shortBuffer, 1);
//				
//				if((shortBuffer[0] == 85 && pos%2 == 00 || (shortBuffer[0] == -86 && pos%2 == 1))){
//					pos++;
//				} else {
//					pos = 0;
//				}
//			}
			
			switch(state){
			//synchronize
			case 0:
				if(pos == 4){
					state = 1;
					pos = 0;
				} else {
					buffer1();
					
					if((buffer1[0] == 85 && pos%2 == 0) || (buffer1[0] == -86 && pos%2 == 1)){
						pos++;
					} else {
						pos = 0;
					}
				}
				break;
			//set checksum
			case 1:
				buffer2();
				checkSum = (buffer2[1] << 8) | buffer2[0];
				state = 2;
				break;
			//compare checksum to sum
			case 2:
				buffer10();
				for(int i = 0; i < 10; i++)sum+=buffer10[i];
				state = 0;
				if(sum == checkSum)state = 3;
				break;
			//get x and y center
			case 3:
				if(XYPos > 1)XYPos = 0;
				x[XYPos] = (buffer10[3] << 8) | buffer10[2];
				y[XYPos] = (buffer10[5] << 8) | buffer10[4];
				XYPos++;
				state = 4;
				break;
			//check for new object
			case 4:
				buffer2();
				if(buffer2[0] == 85 && buffer2[1] == -86)state = 5;
				else state = 0;
				break;
			//check for new frame
			case 5:
				buffer2();
				state = 2;
				if(buffer2[0] == 85 && buffer2[1] == -86)state = 1;
				else checkSum = (buffer2[1] << 8) | buffer2[0];
				break;
			default:
				state = 0;
				break;
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
	
	public void stop(){
		isFinished = true;
	}

}
