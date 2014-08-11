import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Drive
{
	public static void main (String[] args)
	{
		DifferentialPilot pilot = new DifferentialPilot(2.75f, 12.5f, Motor.A, Motor.C);
		TouchSensor LeftBumper = new TouchSensor(SensorPort.S2);
		TouchSensor RightBumper = new TouchSensor(SensorPort.S1);

		Button.waitForAnyPress();
		pilot.forward();
		
		while (Motor.A.isMoving())
		{
			if(LeftBumper.isPressed())
			{
				pilot.stop();
				pilot.travel(-5);
				pilot.rotate(60);
				Motor.A.forward();
				Motor.C.forward();
			}
			if(RightBumper.isPressed())
			{
				pilot.stop();
				pilot.travel(-5);
				pilot.rotate(-60);
				Motor.A.forward();
				Motor.C.forward();
			}
			Button.waitForAnyPress();
			Motor.A.stop();
			Motor.C.stop();
		}	
		Button.waitForAnyPress();
	}
}
