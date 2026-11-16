import java.time.LocalDate;

public class UserInterface {

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
        }
    }
    private void createMember(){
        String name = IO.readln("Indtast navn: ");
        int memberId = Integer.parseInt(IO.readln("Indtast medlems ID: "));
        LocalDate birthday = LocalDate.parse(IO.readln("Indtast fødselsdag (YYYY-MM-DD: "));
    }
}
