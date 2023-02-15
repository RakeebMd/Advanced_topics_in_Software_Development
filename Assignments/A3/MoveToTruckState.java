public class MoveToTruckState extends State{
    public MoveToTruckState(){
        this.minutesRequiredForState=4;
    }
    @Override
    public State timeElapsed(int minutes) {
        minutesElapsed++;
        State state =null;
        if(minutesElapsed>minutesRequiredForState){
            state = Simulation.instance().getFactory().createLoadToTruckState();
        }
        return state;

    }
}
