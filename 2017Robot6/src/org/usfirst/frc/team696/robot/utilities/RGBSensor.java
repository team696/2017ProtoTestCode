//package org.usfirst.frc.team696.robot.utilities;
//
//import edu.wpi.first.wpilibj.I2C;
//
//public class RGBSensor {
//
//    static double seconds;
//    public static Timer timer = new Timer(seconds);
//
//    public static I2C rgbSensor;
//    public static byte sensorAddress = 0x29;
//    public static byte sensorEnableAddress = 0x00;
//    public static byte sensorEnable_PON = 0x01;
//    public static byte sensorEnable_AEN = 0x02;
//    public static byte sensorCommand_Bit = (byte) 0x80;
//    public static byte sensorClearL = 0x14;
//    public static byte sensorRedL = 0x16;
//    public static byte sensorGreenL = 0x18;
//    public static byte sensorBlueL = 0x1A;
//    public static byte[] buffer1 = new byte[1];
//    public static byte[] buffer2 = new byte[2];
//    int[] RGBC = new int[4];
//
//
//
//    public RGBSensor(I2C rgbSensor) {
//
//        rgbSensor = new I2C(I2C.Port.kOnboard, sensorAddress);
//
//        RGBC[0] = read16(sensorClearL);
//        RGBC[1] = read16(sensorRedL);
//        RGBC[2] = read16(sensorGreenL);
//        RGBC[3] = read16(sensorBlueL);
//    }
//
//    public void enable() {
//        rgbSensor.write(sensorEnableAddress, sensorEnable_PON);
//        timer.runTimer(3);
//        rgbSensor.write(sensorEnableAddress, sensorEnable_PON | sensorEnable_AEN);
//
//    }
//
//    public static void write8(byte reg, int value) {
//        rgbSensor.write(sensorCommand_Bit | reg, 1);
//        rgbSensor.write(value & 0xFF, 1);
//    }
//
//    public static byte read8(byte reg) {
////        rgbSensor.write(sensorCommand_Bit, reg);
//        timer.runTimer(3);
//        rgbSensor.read(sensorCommand_Bit | reg, 1, buffer1);
//
//        return reg;
//    }
//
//    public static int read16(byte reg){
//
//        int t;
//        int x;
//
////        rgbSensor.write(sensorCommand_Bit | reg, 2);
////        timer.runTimer(3);
//        rgbSensor.read(sensorCommand_Bit | reg, 2, buffer2);
////        rgbSensor.readOnly(buffer2, 2);
//        t = buffer2[0];
//        x = buffer2[1];
//
//        x <<=8;
//        x |= t;
//
//        return x;
//
//
//    }
//
//    public static void disable() {
//
//        byte reg = 0;
//        reg = read8(sensorEnableAddress);
//        write8(sensorEnableAddress, reg & ~(sensorEnable_PON | sensorEnable_AEN));
//
//    }
//
//}
