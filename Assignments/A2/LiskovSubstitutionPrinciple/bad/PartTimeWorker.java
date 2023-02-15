package LiskovSubstitutionPrinciple.bad;

public class PartTimeWorker extends Worker{
    public PartTimeWorker(int workerID, String workerName, double hourlyWage)
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
// Part time Worker are not eligible for bonus
        System.out.println("Cannot implement calculate bonus for part time worker");
    }

    @Override
    public double salaryCalculator(double hourlyWage) {
        int weeklyHours = 20;
        int no_of_weeks = 4;
        return(weeklyHours * hourlyWage *no_of_weeks);
    }


}
