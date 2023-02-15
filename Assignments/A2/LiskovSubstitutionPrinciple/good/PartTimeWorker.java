package LiskovSubstitutionPrinciple.good;

public class PartTimeWorker extends Worker {
    public PartTimeWorker(int workerID, String workerName, double hourlyWage){

        super(workerID,workerName,hourlyWage);
    }

    @Override
    public void displayWorkerDetails(int workerID) {
        System.out.println("Name of Worker is :"+workerName);
        double salary = salaryCalculator(hourlyWage);
        System.out.println("Monthly salary of Temporary Worker is : $" +salary);
    }

    @Override
    public double salaryCalculator(double hourlyWage) {
        int weeklyHours = 20;
        int no_of_weeks = 4;
        return(weeklyHours * hourlyWage *no_of_weeks);
    }
}
