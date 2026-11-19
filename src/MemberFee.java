import java.time.LocalDate;

public class MemberFee {
    private LocalDate dueDate;
    private Member member;
    private int fee;
    private boolean paid = false;

    public MemberFee(Member member, int fee, LocalDate dueDate, boolean paid) {
        this.member = member;
        this.fee = fee;
        this.dueDate = dueDate;
        this.paid = paid;
    }

    public Member getMember() {
        return member;
    }

    public int getFee() {
        return fee;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        String status = "Ubetalt";

        if (paid) {
            status = "Betalt";
        }
        return "Regning for " + member + "\n" +
                "Beløb: " + fee + "\n" +
                "Betalingsfrist: " + dueDate +
                "\n Status: " + status;
    }
}
