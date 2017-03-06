var drive = Java.type('org.usfirst.frc.team696.robot.commands.Drive(0,90, 0.1, 0, 0, 0, 0.1, 0);');
while(drive.isFinished() && drive.isParallel)()){
	print('running command');
}
