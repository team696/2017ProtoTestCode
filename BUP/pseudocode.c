#define SOC_BUFFER_SIZE 100

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
    
    //Find voltage at lowest current
    unsigned int bestOCVoltage;
    unsigned int bestOCCurrent=0xffff;
    for(unsigned char i=0; i<bufferValidCount; i++){
        if(currentHistory[i] < bestOCCurrent){
            bestOCCurrent = currentHistory[i];
            bestOCVoltage = voltageHistory[i];
        }
    }
    
    //Find MVD
    signed long runningdVdISum = 0;
    unsigned char numValues = 0;
    for(unsigned char i=1; i<bufferValidCount; i++){
        signed int thisdV = voltageHistory[i]-voltageHistory[i-1];
        signed int thisdI = currentHistory[i]-currentHistory[i-1];
        if(thisdI != 0){
            runningdVdISum += (thisdV/thisdI);
            numValues++;
        }
    }
    signed int meandVdI = runningdVdISum/numValues;
    
    
    //Coulomb counting
    static unsigned int coulombs=0;
    static unsigned int milliCoulombs = 0;
    unsigned long thisTimestamp = getTime(); //Some function that returns system time in hundredths of a second
    if(lastRunTimestamp > 0){
        milliCoulombs += ((thisTimestamp - lastRunTimestamp) * currentNow)/10;
        coulombs += milliCoulombs/1000;
        milliCoulombs = milliCoulombs%1000;
    }
    lastRunTimestamp = thisTimestamp;   
    
    if(historyPointer == (SOC_BUFFER_SIZE - 1)){
        historyPointer = 0;
    }
    else{
        historyPointer++;
    }
    
    //Perform weighted average or something
    return SoC
}
    