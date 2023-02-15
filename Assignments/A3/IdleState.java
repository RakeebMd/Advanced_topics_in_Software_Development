public class IdleState extends State{
    public IdleState(){
        this.minutesRequiredForState =0;
    }
    // Executes the logic of the current state for a number of minutes.
    // Returns the state to transition to, or null if should remain
    // in the current state.
    @Override
    public State timeElapsed(int minutes) {
        State state = null;
        if(Simulation.instance().getShelf().getItemCount() ==0){
            state = Simulation.instance().getFactory().createFinishedState();
        }
        else {
            state = Simulation.instance().getFactory().createClaimState();
        }
        return state;
    }
}
