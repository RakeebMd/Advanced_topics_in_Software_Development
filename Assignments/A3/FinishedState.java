public class FinishedState extends State{
    public FinishedState(){
        this.minutesRequiredForState = 0;
    }
    @Override
    public State timeElapsed(int minutes) {
        return null;
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}
