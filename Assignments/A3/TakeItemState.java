public class TakeItemState extends State{
public TakeItemState(){
    this.minutesRequiredForState = 2;
}
    @Override
    public State timeElapsed(int minutes) {
    minutesElapsed++;
    State state =null;
        if(minutesElapsed>minutesRequiredForState){
            state = Simulation.instance().getFactory().createMoveToTruckState();
        }
        return state;
    }
}
