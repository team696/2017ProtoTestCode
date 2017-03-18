package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.I2C;

public class ParsePIXY implements Runnable{

	I2C pixy;
	boolean isFinished = false;
	int pos = 0;
	int state = 0;
	double[] x = {0, 0};
	double[] y = {0, 0};
	int tempCheckSum = 0;
	byte[] buffer2 = new byte[2];
	byte[] buffer10 = new byte[10];
	byte[] buffer1 = new byte[1];
	
	Thread t = new Thread(this);
	
	public ParsePIXY(I2C pixy) {
		// TODO Auto-generated constructor stub
		this.pixy = pixy;
	}
	
	public void start(){
		t.start();
		isFinished = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
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
				//synchroniztion
				case 0:
					if(pos == 4){
						pos = 0;
//						pixy.readOnly(longBuffer, bufferLength);
						state = 1;
					} else {
						buffer1();
						if((buffer1[0] == 85 && pos%2 == 00 || (buffer1[0] == -86 && pos%2 == 1))){
							pos++;
						} else {
							pos = 0;
						}
					}
					break;
				//in sync
				case 1:
					buffer2();
					buffer10();
					for(int i = 0; i < buffer10.length;i++){
						tempCheckSum += buffer10[i];
					}
					
					
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
