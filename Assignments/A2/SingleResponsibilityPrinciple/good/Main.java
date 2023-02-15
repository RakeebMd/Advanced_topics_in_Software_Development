package SingleResponsibilityPrinciple.good;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String studentName = "Lazy";

        GradesCalculator goodSample = new GradesCalculator(studentName);
        FeeCalculator goodSample2 = new FeeCalculator(studentName);

        Map<String, List<String>> courseInfo= new HashMap<String, List<String>>();
        List<String> studentCourses = Arrays.asList("ASDC", "CSCI");
        courseInfo.put(studentName,studentCourses);

        goodSample.calculateGrades("ASDC", Arrays.asList(10,10,30,40));
        goodSample2.feeCalculator(courseInfo);
    }
}
