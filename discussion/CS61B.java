import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CS61B {
    private static String University= "UC Berkeley";
    private int capacity;
    private String semester;
    private CS61BStudent[] students;
    private Map<Integer, CS61BStudent> idToStudent = new HashMap<>();
    public CS61B(int capacity, List<Integer> ids, String semester){
        this.students = new CS61BStudent[capacity];
        this.semester = semester;
        for(int i = 0; i < capacity; i++){
            int id = ids.get(i);
            CS61BStudent student = new CS61BStudent(id);
            students[i] = student;
            idToStudent.put(id, student);

        }

    }
    public void makeAllStudentsWatchLecture(){
        for(CS61BStudent s : students){
            s.watchLecture();
        }
    }
    public int updateGrade(int sid, int points) {
        CS61BStudent student = idToStudent.get(sid);
        student.grade += points;
        return student.grade;
    }
    public static void changeUniversity(String newUniversity){
        CS61B.University = newUniversity;
    }
}
