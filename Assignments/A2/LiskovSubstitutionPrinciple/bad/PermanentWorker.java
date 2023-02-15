package LiskovSubstitutionPrinciple.bad;

public class PermanentWorker extends Worker{
    public PermanentWorker(int workerID,String workerName,double hourlyWage)
    {
        super(workerID,workerName,hourlyWage);

    }

    @Override
    public void displayWorkerDetails(int workerID) {
        System.out.println("Name of Worker is :"+workerName);
        double salary = salaryCalculator(hourlyWage);
        System.out.println("Monthly salary of Worker is : $" +salary);
    }

    @Override
    public void bonusCalculator(double salary) {
        System.out.println("Bonus for the Worker is $:"+(salary * 0.25)+"\n");
    }

    @Override
    public double salaryCalculator(double hourlyWage) {
        int weeklyHours = 40;
        int no_of_weeks = 4;
        return (weeklyHours * hourlyWage *no_of_weeks);
    }


}
