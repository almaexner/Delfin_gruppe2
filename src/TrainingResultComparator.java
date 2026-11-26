import java.util.Comparator;

public class TrainingResultComparator implements Comparator<TrainingResult> {
    @Override
    public int compare(TrainingResult o1, TrainingResult o2) {
        return Integer.compare(o1.getResultTime().convertToMilliseconds(), o2.getResultTime().convertToMilliseconds());
    }
}
