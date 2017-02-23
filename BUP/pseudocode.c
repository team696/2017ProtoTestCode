#define SOC_BUFFER_SIZE 100
#define OCV_WEIGHT 20
#define COULOMB_WEIGHT 50
#define MVD_WEIGHT 30
#define MAX_OC_VOLTAGE 1350 //We need to find this experimentally
#define MIN_OC_VOLTAGE 1000 //We need to find this experimentally
#define MAX_STORED_COULOMBS 10 //We need to find this experimentally
#define MIN_STORED_COULOMBS 8 //We need to find this experimentally
#define MIN_MVD -10 // (Centivolts per amp) //We need to find this experimentally
#define MAX_MVD -100 // (Centivolts per amp) //We need to find this experimentally

//Takes voltage and current as hundredths, e.g., voltageNow=1260 => voltage = 12.60 V
//Returns SoC as percentage, 0-100
unsigned char calculateSoC(unsigned int voltageNow, unsigned int currentNow){
    static unsigned int voltageHistory[SOC_BUFFER_SIZE];
    static unsigned int currentHistory[SOC_BUFFER_SIZE];
    static unsigned char historyPointer=0;
    static char bufferValidCount = 0;
    static unsigned long lastRunTimestamp=0; //In hundredths of a second
    
    //Save current values to circular buffer
    voltageHistory[historyPointer] = voltageNow;
    currentHistory[historyPointer] = currentNow;
    if(bufferValidCount < SOC_BUFFER_SIZE){
        bufferValidCount++;
    }
    
    //-------Find voltage at lowest current-----------
    unsigned int bestOCVoltage;
    unsigned int bestOCCurrent=0xffff;
    for(unsigned char i=0; i<bufferValidCount; i++){
        if(currentHistory[i] < bestOCCurrent){
            bestOCCurrent = currentHistory[i];
            bestOCVoltage = voltageHistory[i];
        }
    }
    //Map OC voltage to SoC (0-100)
    const float OCScalingConstant = 100/(MAX_OC_VOLTAGE - MIN_OC_VOLTAGE);
    unsigned char OCVSoC = 0;
    if(bestOCVoltage > MIN_OC_VOLTAGE){
        OCVSoC = (unsigned char)((OCScalingConstant*bestOCVoltage)-(OCScalingConstant * MIN_OC_VOLTAGE));
    }
    
    //------------Find MVD-----------------
    signed long runningdVdISum = 0;
    unsigned char numValues = 0;
    for(unsigned char i=1; i<bufferValidCount; i++){
        signed int thisdV = voltageHistory[i]-voltageHistory[i-1];
        signed int thisdI = currentHistory[i]-currentHistory[i-1];
        if(thisdI != 0){
            runningdVdISum += ((thisdV*100)/thisdI); //Yields centivolts per amp
            numValues++;
        }
    }
    signed int meanMVD = runningdVdISum/numValues; //We expect this to be a negative value representing change in bus voltage (in centivolts) per amp drawn
    //Map MVD to SoC (0-100)
    unsigned char MVDSoC = 0;
    const float MVDScalingConstant = 100/(MIN_MVD - MAX_MVD);
    if(meanMVD < MAX_MVD){
        MVDSoC = (unsigned char)((meanMVD * MVDScalingConstant)-(MVDScalingConstant * MAX_MVD));
    }
    
    //----------------Coulomb counting----------------
    static unsigned int coulombs=0;
    static unsigned int milliCoulombs = 0;
    unsigned long thisTimestamp = getTime(); //Some function that returns system time in hundredths of a second
    if(lastRunTimestamp > 0){
        milliCoulombs += ((thisTimestamp - lastRunTimestamp) * currentNow)/10;
        coulombs += milliCoulombs/1000;
        milliCoulombs = milliCoulombs%1000;
    }
    lastRunTimestamp = thisTimestamp;   
    //Map used Coulombs to SoC (0-100)
    unsigned char CoulombSoC = 0;
    const float CoulombScalingConstant = 100/(MIN_STORED_COULOMBS - MAX_STORED_COULOMBS);
    if(coulombs < (MAX_STORED_COULOMBS - MIN_STORED_COULOMBS)){
        CoulombSoC = (unsigned char)(CoulombScalingConstant*coulombs+100.);
    }
    
    if(historyPointer == (SOC_BUFFER_SIZE - 1)){
        historyPointer = 0;
    }
    else{
        historyPointer++;
    }
    
    //--------------Weighted average------------
    unsigned int unscaledSoC = (OCV_WEIGHT * OCVSoC) + (MVD_WEIGHT * MVDSoC) + (COULOMB_WEIGHT * CoulombSoC); //Gives SoC out of 10,000
    return (unsigned char)(unscaledSoC/100);
}
    