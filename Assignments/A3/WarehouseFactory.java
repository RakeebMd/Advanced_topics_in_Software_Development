public class WarehouseFactory implements IWarehouseFactory {
    @Override
    public IBattery createBattery(int capacity) {
        return new Battery(capacity);
    }

    @Override
    public IRobot createRobot(int batteryCapacity) {
        IBattery battery = createBattery(batteryCapacity);
        // TODO: Robots start in the IdleState, instantiate that here.
        State startState = createIdleState(); //makeIdleState();
        return new Robot(battery, startState);
    }

    public IRobot createRobotWithBatteryPack(int batteryCapacity, int packCapacity) {

        // TODO: Implement the Decorator pattern, and use it to decorate a regular
        //       battery with a battery pack, and create a robot with the battery pack.
        IBattery battery =  createBattery(batteryCapacity);
        State startState = createIdleState();
        IBattery batteryPack = new BatteryPackDecorator(battery,packCapacity);
        return new Robot(batteryPack,startState);
    }

    @Override
    public State createIdleState() {
        return new IdleState();
    }

    @Override
    public State createFinishedState() {
        return new FinishedState();
    }

    @Override
    public State createClaimState() {
        return new ClaimItemState();
    }

    @Override
    public State createRechargeState(State precedingState, IBattery battery) {
        return new RechargeState(precedingState, battery);
    }

    @Override
    public State createMoveToShelfState() {
        return new MoveToShelfState();
    }

    @Override
    public State createTakeItemState() {
        return new TakeItemState();
    }

    @Override
    public State createMoveToTruckState() {
        return new MoveToTruckState();
    }

    @Override
    public State createLoadToTruckState() {
        return new LoadTruckState();
    }


}
