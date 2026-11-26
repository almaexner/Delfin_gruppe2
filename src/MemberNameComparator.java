import java.text.Collator;
import java.util.Comparator;

public class MemberNameComparator implements Comparator<Member> {

    @Override
    public int compare(Member member1, Member member2) {
        return member1.getName().compareToIgnoreCase(member2.getName());
    }
}
