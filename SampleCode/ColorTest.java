ColorTest.javaOpen
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.*;
import lejos.robotics.localization.*;
import lejos.robotics.Color;
import lejos.util.Delay;
import lejos.geom.Point;

public class ColorTest
{
	public static void main(String[] Args)
	{
		ColorSensor light = new ColorSensor(SensorPort.S2);
		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S1);
		DifferentialPilot pilot = new DifferentialPilot(3.20f, 12.5f, Motor.B, Motor.C);
		OdometryPoseProvider pp = new OdometryPoseProvider(pilot);
		Pose pose = new Pose();
		pose.setLocation(0,0);

		
		//pilot.forward();
		//while(true)
		//{
		//	LCD.clear();
		//	LCD.drawInt(light.getColorID(), 0,0,0);
		//	LCD.drawInt(sonic.getDistance(), 0,0,1);
		//	Delay.msDelay(200);
		//}
		pilot.rotateLeft();
		
		if(sonic.getDistance()<=70)
			{	
				pilot.stop();
				//Motor.A.rotate(-360);
				pilot.forward();
				if(sonic.getDistance()<=5)
				{
					pilot.stop();
					pilot.rotate(-10);
					if(light.getColorID()==6)
					{
						Point p = pose.getLocation();
						pilot.travel(-20);
						pilot.rotateTo(0,0);
						pilot.travelTo(0,0);
						pilot.rotateTo(p);
					}
				}
			}
	}
}
Displaying ColorTest.java.
