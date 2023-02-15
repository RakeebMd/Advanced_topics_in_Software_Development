public class LoadTruckState extends State {

    public LoadTruckState() {
        this.minutesRequiredForState = 5;
    }

    @Override
    public State timeElapsed(int minutes) {
        minutesElapsed ++;
        if(minutesElapsed>minutesRequiredForState) {
            return Simulation.instance().getFactory().createIdleState();
        }
        else {
            return null;
        }
    }
}
