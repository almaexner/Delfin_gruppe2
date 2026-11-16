import java.time.LocalDate;

public class UserInterface {
    private ClubManagement clubManagement;

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput = Integer.parseInt(IO.readln("Vælg bruger: " +
                    "1. Ledelse" +
                    "2. Kasserer" +
                    "3. Medlem"));
            switch (userInput) {
                case 1 -> managementMenu();
                case 2 -> financeMenu();
                case 3 -> memberMenu();
                case 0 -> isRunning = false;
                default -> IO.println("Ugyldigt valg. Prøv igen");
            }
        }
    }

    private void managementMenu() {

    }

    private void financeMenu() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput = Integer.parseInt(IO.readln("Indtast dit valg: " +
                    "1. Se betalinger" +
                    "2. Medlemmer i restance" +
                    "3. "));
        }

    }

    private void memberMenu() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput = Integer.parseInt(IO.readln("Indtast dit valg: " +
                    "1. Opret ny svømmer" +
                    "2. Ændrer medlemsstatus (aktiv/passiv)" +
                    "3. Betal kontigent"));
            switch (userInput){
                case 1 -> createMember();
                case 2 -> changeMembershipStatus();
            }
        }
    }
    public void payFee(){
        int memberId = Integer.parseInt(IO.readln("Angiv medlemsnummer: "));
        Member memberFound = null;
        for (Member member : clubManagement.getMembers()){
            if (member.getMemberId() == memberId){
                memberFound = member;
            }
        }
        PaymentResult result = clubManagement.makePayment(memberFound);
        switch (result){
            case PASSIVE -> IO.println("Passiv: Du har følgende regning: " + clubManagement.getMemberFee(memberId));
            case YOUTH_ACTIVE -> IO.println("Ung: Du har følgende regning: " + clubManagement.getMemberFee(memberId));
            case SENIOR_ACTIVE -> IO.println("Senior: Du har følgende regning: " + clubManagement.getMemberFee(memberId));
            case SENIOR_ACTIVE_DISCOUNT -> IO.println("Seniorrabat: Du har følgende regning: " + clubManagement.getMemberFee(memberId));

        }
    private void createMember(){
        String name = IO.readln("Indtast navn: ");
        int memberId = Integer.parseInt(IO.readln("Indtast medlems ID: "));
        LocalDate birthday = LocalDate.parse(IO.readln("Indtast fødselsdag (YYYY-MM-DD: "));
        MembershipType membershipType = chooseMembershipStatus();

        Member member = new Member(name, memberId, birthday, membershipType);

        clubManagement.addMember(member);
    }

    private MembershipType chooseMembershipStatus(){
        while (true) {
            String userInput = IO.readln("Vælg aktivitetsstatus: " +
                    "1. Aktiv" +
                    "2. Passiv");
            if (userInput.equals("1")){
                return MembershipType.ACTIVE;
            } else if (userInput.equals("2")) {
                return MembershipType.PASSIVE;
            } else {
                IO.println("Ugyldigt valg");
            }
        }
    }

    private void changeMembershipStatus(){
        int memberId = Integer.parseInt(IO.readln("Indtast medlems id: "));
        Member member = clubManagement.findMemberByMemberId(memberId);

        if (member == null){
            IO.println("Der findes intet medlem med dette medlems id.");
            return;
        }

        MembershipType membershipType = chooseMembershipStatus();
        member.setMembershipType(membershipType);
        IO.println("Medlemsstatus er ændret for: " + member.getName());
    }
}
