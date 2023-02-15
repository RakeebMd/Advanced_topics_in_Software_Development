package LiskovSubstitutionPrinciple.good;

public abstract class Worker {
    public int workerID;
    public String workerName;
    public double hourlyWage;
    public Worker(int workerID, String workerName, double hourlyWage){
        this.workerID= workerID;
        this.workerName = workerName;
        this.hourlyWage = hourlyWage;
    }
    public abstract void displayWorkerDetails(int workerID);
    public abstract double   salaryCalculator(double hourlyWage);
}
