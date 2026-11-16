import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClubManagement {
    List<Member> members = new ArrayList<>();
    List<Swimmer> swimmers = new ArrayList<>();
    List<MemberFee> fees = new ArrayList<>();
    UserInterface ui;

    public ClubManagement(){

    }
    public void addFee(MemberFee memberFee){
        fees.add(memberFee);
    }
    public void addMember(Member member){
        members.add(member);
    }
    public void removeMember(Member member){
        members.remove(member);
    }
    public void addSwimmer(Swimmer swimmer){
        swimmers.add(swimmer);
    }
    public void removeSwimmer(Swimmer swimmer){
        swimmers.remove(swimmer);
    }
    public List<Member> getMembers(){
        return members;
    }
    public List<Swimmer> getSwimmers(){
        return swimmers;
    }
    public List<MemberFee> getFees(){
        return fees;
    }
    public PaymentResult makePayment(Member member){
        int memberAge = member.calculateAge(member.getDateOfBirth());
        MembershipType membershipType = member.getMembershipType();
        PassiveFee passiveFee = new PassiveFee();
        YouthFee youthFee = new YouthFee();
        SeniorFee seniorFee = new SeniorFee(memberAge);
        if (membershipType.equals(MembershipType.ACTIVE) && (memberAge < 18)){
            addFee(new MemberFee(member, youthFee.calculateFee(), youthFee.calculateDueDate(LocalDate.now())));
            return PaymentResult.YOUTH_ACTIVE;
        }
        if (membershipType.equals(MembershipType.ACTIVE) && (memberAge <= 60)){
            addFee(new MemberFee(member, youthFee.calculateFee(), seniorFee.calculateDueDate(LocalDate.now())));
            return PaymentResult.SENIOR_ACTIVE;
        }
        if (membershipType.equals(MembershipType.ACTIVE) && (memberAge > 60)){
            addFee(new MemberFee(member, seniorFee.calculateFee(), seniorFee.calculateDueDate(LocalDate.now())));
            return PaymentResult.SENIOR_ACTIVE_DISCOUNT;
        }
        else {
            addFee(new MemberFee(member, passiveFee.calculateFee(), passiveFee.calculateDueDate(LocalDate.now())));
            return PaymentResult.PASSIVE;
        }
    }
    public MemberFee getMemberFee(int memberId){
        Member memberFound = null;
        for (Member member : members){
            if (member.getMemberId() == memberId){
                memberFound = member;
            }
        }
        if (memberFound != null){
            for (MemberFee memberFee : fees){
                if (memberFee.getMember().equals(memberFound)){
                    return memberFee;
                }
            }
        }
        return null;
    }
}
