public class MoveToShelfState extends State {

    public MoveToShelfState() {
        this.minutesRequiredForState = 4;
    }
    @Override
    public State timeElapsed(int minutes) {
        minutesElapsed ++;
        State state = null;
        if(minutesElapsed >minutesRequiredForState) {
            state = Simulation.instance().getFactory().createTakeItemState();
        }
        return state;
    }
}
