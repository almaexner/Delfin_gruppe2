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
            }
        }
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

    private void changeMembershipStatus(Member member){
        MembershipType membershipType = chooseMembershipStatus();
        member.setMembershipType(membershipType);
    }
}
