public class BatteryDecorator implements IBattery {
    protected IBattery battery;

    public BatteryDecorator(IBattery battery) {
        this.battery = battery;
    }

    @Override
    public boolean hasEnoughPowerForMinutes(int minutes) {

        return battery.hasEnoughPowerForMinutes(minutes);
    }

    @Override
    public boolean isFullyCharged() {
        return battery.isFullyCharged();
    }

    @Override
    public void drain(int minutes) {
        battery.drain(minutes);
    }

    @Override
    public void recharge(int minutes) {
        battery.recharge(minutes);
    }
}
