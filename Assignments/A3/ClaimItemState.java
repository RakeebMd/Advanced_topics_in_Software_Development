public class ClaimItemState extends State{
    public ClaimItemState() {
        this.minutesRequiredForState=1;
    }
    @Override
    public State timeElapsed(int minutes) {
        minutesElapsed++;
        State state = null;
        if(Simulation.instance().getShelf().getItemCount()==0){
            state = Simulation.instance().getFactory().createIdleState();
        }
        else {
            if(minutesElapsed>minutesRequiredForState) {
                Simulation.instance().getShelf().claimItem();
                state = Simulation.instance().getFactory().createMoveToShelfState();
            }
            }
        return state;
    }
}
