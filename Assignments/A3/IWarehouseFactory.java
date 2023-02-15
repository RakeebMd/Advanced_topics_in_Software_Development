public interface IWarehouseFactory
{
	// Robots and batteries
	public IBattery createBattery(int capacity);
	public IRobot createRobot(int batteryCapacity);
	public IRobot createRobotWithBatteryPack(int batteryCapacity, int packCapacity);
	// States
	// .. You must complete the interface with methods to instantiate your state objects.
	public State createIdleState();
	public State createFinishedState();
	public State createClaimState();
	public State createRechargeState(State precedingState,IBattery battery);
	public State createMoveToShelfState();
	public State createTakeItemState();
	public State createMoveToTruckState();
	public State createLoadToTruckState();


}
