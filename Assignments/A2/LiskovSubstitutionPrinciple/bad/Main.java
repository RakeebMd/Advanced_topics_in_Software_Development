package LiskovSubstitutionPrinciple.bad;

public class Main {
public static void main(String[] args){
    int id1 = 9001;
    String wName1= "David Neesham";
    double hrlyWage1 = 40.50;
    double salary1=0.0;

    int id2 =10010;
    String wName2 = "Mitchell Smith";
    double hrlyWage2 = 15.25;
    double salary2 =0.0;

    /*PermanentWorker permanentWorker = new PermanentWorker(id1,wName1,hrlyWage1);
    permanentWorker.displayWorkerDetails(id1);
    salary1 = permanentWorker.salaryCalculator(hrlyWage1);
    permanentWorker.bonusCalculator(salary1);

    TemporaryWorker temporaryWorker = new TemporaryWorker(id2,wName2,hrlyWage2);
    temporaryWorker.displayWorkerDetails(id2);
    salary2=temporaryWorker.salaryCalculator(hrlyWage2);
    temporaryWorker.bonusCalculator(salary2);*/
    Worker w1 = new PermanentWorker(id1,wName1,hrlyWage1);
    w1.displayWorkerDetails(id1);
    salary1 = w1.salaryCalculator(hrlyWage1);
    w1.bonusCalculator(salary1);

    Worker w2 = new PartTimeWorker(id2,wName2,hrlyWage2);
    w2.displayWorkerDetails(id1);
    salary2 = w2.salaryCalculator(hrlyWage2);
    w2.bonusCalculator(salary2);
    }
}
