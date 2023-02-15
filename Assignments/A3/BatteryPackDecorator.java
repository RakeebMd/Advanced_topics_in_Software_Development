public class BatteryPackDecorator extends BatteryDecorator {

    private int batteryPackCapacity;
    private int availableCharge;

    public BatteryPackDecorator(IBattery battery, int batteryPackCapacity) {
        super(battery);
        this.batteryPackCapacity = batteryPackCapacity;
        this.availableCharge = batteryPackCapacity;
    }

    public boolean isFullyCharged() {
        return (availableCharge == batteryPackCapacity);
    }


    public void drain(int minutes) {
        if (hasBatteryPackEnoughPowerForMinutes(minutes)) {
            batteryPackDrain(minutes);
        } else {
            super.drain(minutes);
        }

    }

    public void batteryPackDrain(int minutes) {
        availableCharge -= minutes;
        availableCharge = Math.max(availableCharge, 0);
    }

    public boolean hasBatteryPackEnoughPowerForMinutes(int minutes) {
        if (availableCharge - minutes >= 0) {
            return true;
        }
        return false;
    }

    public void recharge(int minutes) {
        super.recharge(minutes);
        batteryPackRecharge(minutes);
    }
    public void batteryPackRecharge(int minutes){
        availableCharge += minutes * 2;
        availableCharge = Math.min(batteryPackCapacity, availableCharge);
    }
}
