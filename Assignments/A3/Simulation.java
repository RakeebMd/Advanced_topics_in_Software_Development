import java.util.*;

public class Simulation
{
	private static Simulation theOneInstance = null;
	private IWarehouseFactory factory;
	private List<Object> robots;
	private int minutesToCompleteSimulation;
	private Shelf shelf;
	
	public Simulation()
	{
		robots = new ArrayList<Object>();
		minutesToCompleteSimulation = 0;
		shelf = new Shelf();
	}
	
	public static Simulation instance()
	{
		if (null == theOneInstance)
		{
			theOneInstance = new Simulation();
		}
		return theOneInstance;
	}
	
	public IWarehouseFactory getFactory()
	{
		return factory;
	}
	
	public Shelf getShelf()
	{
		return shelf;
	}
	
	public void build(Arguments args, IWarehouseFactory factory)
	{

		// TODO: Complete this method to use the factory to build all of the
		//       objects needed for the simulation.
		this.factory = factory;
		shelf.setItemCount(args.getShelfCount());
		for (int i=0;i< args.getNumRobots()- args.getNumBatteryPacks();i++){
			Robot robot = (Robot) factory.createRobot(args.getDefaultBatteryCapacity());
			robots.add(robot);
			TimerSubject.instance().attachObserver(robot);
		}
		for(int i=0;i<args.getNumBatteryPacks();i++){
			Robot robot = (Robot) factory.createRobotWithBatteryPack(args.getDefaultBatteryCapacity(),args.getBatteryPackCapacity());
			robots.add(robot);
			TimerSubject.instance().attachObserver(robot);
		}
	}
	
	public int run()
	{
		System.out.println("Simulation begun!");
		boolean robotsStillWorking = true;
		while (robotsStillWorking)
		{
			// TODO: Notify your Observer pattern subject here that 1 minute has passed.
			minutesToCompleteSimulation += 1;
			TimerSubject.instance().notifySubjects();
			// Check all robots to see whether they are finished.
			robotsStillWorking = false;
			ListIterator<Object> iter = robots.listIterator();
			while (iter.hasNext())
			{
				IRobot robot = (IRobot)iter.next();
				if (robot.isWorking())
				{
					robotsStillWorking = true;
					break;
				}
			}
		}
		System.out.println("Simulation complete!");
		return minutesToCompleteSimulation;
	}
}

















