import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.ButtonCounter;

public class SteerTest
{
	public static void main (String[] args)
	{
		DifferentialPilot pilot = new DifferentialPilot(2.75f, 12.5f, Motor.A, Motor.C);
		ButtonCounter bc = new ButtonCounter();
		while (true)
		{
			bc.count("Turn Rate x10");
			int turnRate = 100*bc.getLeftCount()+10*bc.getRightCount();
			bc.count("Angle x10");
			int angle = 100*bc.getLeftCount() + 10*bc.getRightCount();
			pilot.steer(turnRate,angle);
		}
	}
}
Displaying SteerTest.java.
