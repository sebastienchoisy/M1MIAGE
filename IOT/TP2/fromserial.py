"""
Graph from ESP32 Serial link
Run photoresitor sketch in same time !

Author : G.MENEZ

"""
import sys, serial
import numpy as np
import json
from time import sleep
from collections import deque
from matplotlib import pyplot as plt
from matplotlib.widgets import Button
import msvcrt

#=============================================

class AnalogData:
    
    def __init__(self, maxLen=100):
        """ Ring Buffer de maxLen samples """        
        self.x = []
        self.y = []
        self.maxLen = maxLen

    def add(self, x, y):
        """ Add a sample in the  ring buffer """
        if len(self.x) == self.maxLen:
            self.x.pop(0)
            self.y.pop(0)
                        
        self.x.append(x)
        self.y.append(y)
    
#=============================================

class AnalogPlot:
    """ Plotting Figure """
    def __init__(self, analogData):
        plt.ion()                 # set plot to "animated"
        self.fig = plt.figure()  # first plot with temperature
        self.tempAx = self.fig.add_subplot(411)
        self.decoration('Temperature')
        plt.xlabel('duration')
        plt.ylabel('temperature')
        self.tempAxline, = self.tempAx.plot(analogData.x, analogData.y,
                                    'r.-', label="TEMP")

        self.intAx = self.fig.add_subplot(414)
        self.decoration('Light Intensity')
        plt.xlabel('duration')
        plt.ylabel('intensity')
        self.intAxline, = self.intAx.plot(analogData.x, analogData.y,
                                    'r.-', label="INT")
        redLightAxes = plt.axes([0.60, 0.5, 0.15, 0.075])
        self.redLightButton = Button(redLightAxes, 'Red Light',color="grey")
        yellowLightAxes = plt.axes([0.30, 0.5, 0.15, 0.075])
        self.yellowLightButton = Button(yellowLightAxes, 'Yellow Light',color="grey")
        plt.show()


    def updateplot(self, analogTemperatureData, analogIntensityData, redLight, yellowLight):
        """  update plot """
        self.tempAxline.set_xdata(analogTemperatureData.x)
        self.tempAxline.set_ydata(analogTemperatureData.y)
        self.intAxline.set_xdata(analogIntensityData.x)
        self.intAxline.set_ydata(analogIntensityData.y)  
        if redLight == "HIGH":
            self.redLightButton.ax.set_facecolor('red')
        else:
            self.redLightButton.ax.set_facecolor('grey')

        if yellowLight == "HIGH":
            self.yellowLightButton.ax.set_facecolor('yellow')
        else:
            self.yellowLightButton.ax.set_facecolor('grey')
            
        self.fig.canvas.draw()
        self.fig.canvas.flush_events()
        #        self.ax.autoscale()
        self.tempAx.relim()            # reset intern limits of the current axes
        self.tempAx.autoscale_view()   # reset axes limits
        self.intAx.relim()            # reset intern limits of the current axes
        self.intAx.autoscale_view()
        self.intAx.set_ybound(analogIntensityData.y[len(analogIntensityData.y)-1]-1000,analogIntensityData.y[len(analogIntensityData.y)-1]+1000)
        self.tempAx.set_ybound(analogTemperatureData.y[len(analogTemperatureData.y)-1]-10,analogTemperatureData.y[len(analogTemperatureData.y)-1]+10)
    
    def decoration(self, title):
        plt.title(title)
        plt.grid(True)
        plt.legend()
        
#----------------  Main function -----------------
def main():
    # Ring buffer of last samples 
    tempAnalogData = AnalogData()
    intAnalogData = AnalogData()
    # plotting canvas
    analogPlot = AnalogPlot(tempAnalogData)    
    
    # open serial port
    ser = serial.Serial(
        port='COM3',
        baudrate = 9600,
        parity=serial.PARITY_NONE,
        stopbits=serial.STOPBITS_ONE,
        bytesize=serial.EIGHTBITS,
        timeout=1
    )
    
    counter=0
    commandprint=''
  
    while True:
        try:
            # Read 
            jsonPayload = ser.readline()
            jsonPayload = jsonPayload.rstrip()
            try : 
                  jsonPayload = jsonPayload.decode("utf-8")
            except UnicodeError: 
                continue
            d = json.loads(jsonPayload)

            if int(d["intensity"]) < 200:
                redLedValue="HIGH"
                yellowLedValue="LOW"
            elif int(d["intensity"]) > 1000:
                redLedValue="LOW"
                yellowLedValue="HIGH"
            else:
                redLedValue="LOW"
                yellowLedValue="LOW"

            ledStates = {
                "redLed":redLedValue,
                "yellowLed":yellowLedValue
            }

            sent_payload = json.dumps(ledStates,separators=(',', ':'))
            ser.write(sent_payload.encode('ascii'))
  
            # Plot
            tempAnalogData.add(counter, d["temperature"])
            intAnalogData.add(counter, d["intensity"])
            analogPlot.updateplot(tempAnalogData,intAnalogData,redLedValue,yellowLedValue)

        
        
        except KeyboardInterrupt:
            print('exiting')
            break
        
        if msvcrt.kbhit():
            commandprint = commandprint + msvcrt.getch();
            if msvcrt.getch()=='\r':
                print(commandprint)
        
        counter += 1

    
    # close serial
    ser.flush()
    ser.close()
    
#------------------ call main -----------------------

if __name__ == '__main__':
    main()
