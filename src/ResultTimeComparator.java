import java.util.Comparator;

public class ResultTimeComparator implements Comparator<DisciplinResult> {
    @Override
    public int compare(DisciplinResult o1, DisciplinResult o2) {
        return Double.compare(o1.getResultTime(), o2.getResultTime());
    }
}
