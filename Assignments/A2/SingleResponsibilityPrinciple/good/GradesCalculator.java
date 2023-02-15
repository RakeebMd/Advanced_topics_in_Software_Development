package SingleResponsibilityPrinciple.good;

import java.util.List;

public class GradesCalculator {

    protected String studentName;

    public GradesCalculator(String studentName){
        this.studentName = studentName;
    }

    public void calculateGrades(String courseName, List<Integer> marksObtained)
    {
        int totalMarks=0;
        String grade ="";
        for (int i=0;i< marksObtained.size();i++)
        {
            totalMarks  = totalMarks+marksObtained.get(i);
        }
        if (totalMarks > 95) {
            grade = "A+";
        } else if ((totalMarks > 85) && (totalMarks <= 90)) {
            grade = "A";
        } else if ((totalMarks > 80) && (totalMarks <=85)) {
            grade = "A-";
        } else if ((totalMarks > 76) && (totalMarks <=80)) {
            grade = "B+";
        } else if ((totalMarks >= 71) && (totalMarks <=76)) {
            grade = "B";
        } else if(totalMarks <=70) {
            grade = "F";
        }
        System.out.println("Total marks is :"+totalMarks);
        System.out.println("Student =>"+studentName+" has secured: '"+grade+ "' in the course: "+courseName);
    }
}
