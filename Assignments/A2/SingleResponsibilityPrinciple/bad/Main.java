package SingleResponsibilityPrinciple.bad;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String studentName = "Lazy";
        Calculator badSample = new Calculator(studentName);

        Map<String, List<String>> courseInfo= new HashMap<String, List<String>>();
        List<String> studentCourses = Arrays.asList("ASDC", "DBMS");
        courseInfo.put(studentName,studentCourses);
        badSample.feeCalculator(courseInfo);
        badSample.calculateGrades("DBMS", Arrays.asList(9,10,15,20));
    }
}
