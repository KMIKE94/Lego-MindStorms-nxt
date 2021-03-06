import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class SquareTracer
{
	DifferentialPilot pilot;
	
	public void drawSquare(float length)
	{
		for(int i =0;i<4;i++)
		{
			pilot.travel(length);
			pilot.rotate(90);
		}
	}
	public static void main(String[] args)
	{
		SquareTracer sq = new SquareTracer();
		sq.pilot = new DifferentialPilot(2.75f, 12.5f, Motor.A, Motor.C);
		sq.drawSquare(20);
	}
}
Displaying SquareTracer.java.
