import java.time.LocalDate;

public class UserInterface {
    private ClubManagement clubManagement;

    public UserInterface(ClubManagement clubManagement) {
        this.clubManagement = clubManagement;
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput = Integer.parseInt(IO.readln("Vælg bruger: \n" +
                    "1. Ledelse\n" +
                    "2. Kasserer\n" +
                    "3. Træner\n" +
                    "4. Medlem\n"));
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
            int userInput = Integer.parseInt(IO.readln("Indtast dit valg: \n" +
                    "1. Se betalinger\n" +
                    "2. Medlemmer i restance\n" +
                    "3. "));
        }

    }

    private void trainerMenu() {

    }

    private void memberMenu() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput = Integer.parseInt(IO.readln("Indtast dit valg:\n" +
                    "1. Opret ny svømmer\n" +
                    "2. Ændrer medlemsstatus (aktiv/passiv)\n" +
                    "3. Betal kontigent\n"));
            switch (userInput) {
                case 1 -> createMember();
                case 2 -> changeMembershipStatus();
                case 3 -> payFee();
            }
        }
    }

    public void payFee() {
        int memberId = Integer.parseInt(IO.readln("Angiv medlemsnummer: "));
        Member memberFound = null;
        for (Member member : clubManagement.getMembers()) {
            if (member.getMemberId() == memberId) {
                memberFound = member;
            }
        }
        PaymentResult result = clubManagement.makePayment(memberFound);
        switch (result) {
            case PASSIVE -> IO.println("Passiv: Du har følgende regning: " + clubManagement.getMemberFee(memberId));
            case YOUTH_ACTIVE -> IO.println("Ung: Du har følgende regning: " + clubManagement.getMemberFee(memberId));
            case SENIOR_ACTIVE ->
                    IO.println("Senior: Du har følgende regning: " + clubManagement.getMemberFee(memberId));
            case SENIOR_ACTIVE_DISCOUNT ->
                    IO.println("Seniorrabat: Du har følgende regning: " + clubManagement.getMemberFee(memberId));

        }
    }

    private void createMember() {
        String name = IO.readln("Indtast navn: ");
        int memberId = Integer.parseInt(IO.readln("Indtast medlems ID: "));
        LocalDate birthday = LocalDate.parse(IO.readln("Indtast fødselsdag (YYYY-MM-DD: "));
        MembershipType membershipType = chooseMembershipStatus();
        int newMemberId = clubManagement.getNewMemberId(clubManagement.numbers);
        Member member = new Member(name, newMemberId, birthday, membershipType, null);
        member.setSwimmerAgeType();

        clubManagement.addMember(member);

    }

    private MembershipType chooseMembershipStatus() {
        while (true) {
            String userInput = IO.readln("Vælg aktivitetsstatus: \n" +
                    "1. Aktiv\n" +
                    "2. Passiv\n");
            if (userInput.equals("1")) {
                return MembershipType.ACTIVE;
            } else if (userInput.equals("2")) {
                return MembershipType.PASSIVE;
            } else {
                IO.println("Ugyldigt valg");
            }
        }
    }

    private void changeMembershipStatus() {
        int memberId = Integer.parseInt(IO.readln("Indtast medlems id: "));
        Member member = clubManagement.findMemberByMemberId(memberId);

        if (member == null) {
            IO.println("Der findes intet medlem med dette medlems id.");
            return;
        }

        MembershipType membershipType = chooseMembershipStatus();
        member.setMembershipType(membershipType);
        IO.println("Medlemsstatus er ændret for: " + member.getName());
    }
}
