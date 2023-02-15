import java.util.ArrayList;
import java.util.List;

public class PartNumberDatabaseMock implements PartNumberDatabase
{
    public ArrayList<Integer> dbObject = new ArrayList<Integer>();
    public void dbValidation(){
        dbObject.add(1234);
        dbObject.add(7777);
        dbObject.add(9999);
    }
    public PartNumberDatabaseMock(){
        dbValidation();
    }

    @Override
    public List<Integer> validatePartNumbers() {

        return dbObject;
    }
}
