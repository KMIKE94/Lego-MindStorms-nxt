import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.*;

public class Drive
{
	public static void main (String[] args)
	{
		DifferentialPilot pilot = new DifferentialPilot(3.20f, 12.5f, Motor.C, Motor.B);
		ColorSensor cs = new ColorSensor(SensorPort.S2);
		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S1);

		Motor.B.setSpeed(180);
		Motor.C.setSpeed(180);
		
		
		
		int object1color=8, object1distance=300;
		
		//Check color
		cs.setFloodlight(2);
		int angle=720;
		int calibrate=0;
		int d = 50;		//range of ultrasonic sensor
		int i = 10;		//increment of movement in degrees
		int j = 3;		//increment of precise movement in degrees
		int l = 10;		//rotation in degrees to line up color sensor
		int c = 50;		//degrees rotation to get clear of object
		int count=0;
		int object=0;	//number of objects found
		int scanned=0;
		
		//Search for objects
		while(angle>=360)
		{
			pilot.rotate(-i);
			angle=angle-i;
			
				//object 1
				if(object==0&&sonic.getDistance()<=d&&angle>=360)
				{
					calibrate=0;
					pilot.stop();
					while(sonic.getDistance()<=d)
					{
						pilot.rotate(-j);
						angle=angle-j;
					}
					pilot.rotate(i);
					angle=angle+i;
					while(sonic.getDistance()<=d)
					{
						pilot.rotate(j);
						calibrate=calibrate-j;
						angle=angle+j;
					}
					pilot.rotate(calibrate/2 -3);
					object1distance=sonic.getDistance();
					pilot.travel(object1distance-5);
					pilot.rotate(l);
					object1color=cs.getColorID();
					pilot.rotate(-l);
					pilot.travel(5-object1distance);
					pilot.rotate(-c);
					angle=angle-c;
					object=object+1;
				}
		
		}
		int counter = 0;
		//Shoot targets
		while(angle<=360 && counter < 10)
		{
			pilot.rotate(-i);
			angle=angle-i;
			
			if(object>=1&&sonic.getDistance()<=d&&angle>=0&&object1color!=2&&scanned==0)
			{
				calibrate=0;
				pilot.stop();
				while(sonic.getDistance()<=d)
				{
					pilot.rotate(-j);
					angle=angle-j;
				}
				pilot.rotate(i);
				angle=angle+i;
				while(sonic.getDistance()<=d)
				{
					pilot.rotate(j);
					calibrate=calibrate-j;
					angle=angle+j;
				}
				pilot.rotate(calibrate/2 -3);
				cs.setFloodlight(0);
				Motor.A.rotate(-360);
				cs.setFloodlight(2);
				pilot.rotate(-c);
				angle=angle-c;
				scanned++;
			}
			else if (sonic.getDistance()<=d&&object1color==2)
			{
				pilot.rotate(-c);
				angle=angle-c;
				scanned++;
			}
			counter++;
		}
		
	}	
}
