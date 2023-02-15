package SingleResponsibilityPrinciple.good;

import java.util.List;
import java.util.Map;

public class FeeCalculator extends GradesCalculator {
    public FeeCalculator(String studentName) {
        super(studentName);
    }

    int termFee = 0;
    int courseFee = 3000;

    public void feeCalculator(Map<String, List<String>> courseName) {
        if (courseName.containsKey(studentName)) {
            termFee = courseName.get(studentName).size() * courseFee;
        } else {
            System.out.println("Student not available");
        }
        System.out.println("Term fee for the student: " + studentName + " is :" + termFee);
    }
}
