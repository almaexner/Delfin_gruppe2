import java.util.Comparator;

public class ResultTimeComparator implements Comparator<DisciplinResult> {
    @Override
    public int compare(DisciplinResult o1, DisciplinResult o2) {
        return Integer.compare(o1.getResultTime().convertToMilliseconds(), o2.getResultTime().convertToMilliseconds());
    }
}
