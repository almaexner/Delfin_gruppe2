import java.time.LocalDate;

public class MemberFee {
    LocalDate dueDate;
    Member member;
    int fee;

    public MemberFee(Member member, int fee, LocalDate dueDate){
        this.member = member;
        this.fee = fee;
        this.dueDate = dueDate;
    }

    public Member getMember() {
        return member;
    }
    @Override
    public String toString(){
        return "Regning for " + member + "\n" +
                "Beløb: " + fee + "\n" +
                "Betalingsfrist: " + dueDate;
    }
}
