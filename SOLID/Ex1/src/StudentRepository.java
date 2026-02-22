import java.util.List;

public interface StudentRepository {
  void save(StudentRecord s);
  int count();
  List<StudentRecord> all();

}
