public class RechargeState extends State{
    private IBattery battery;
    private State precedingState;

    public RechargeState(State precedingState,IBattery battery){
        this.minutesRequiredForState=0;
        this.precedingState = precedingState;
        this.battery = battery;
    }
    @Override
    public State timeElapsed(int minutes) {
        while(!battery.isFullyCharged()){
            battery.recharge(minutes);
        }
        return precedingState;
    }
}
