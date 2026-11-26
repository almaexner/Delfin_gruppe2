import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class UserInterface {
    private ClubManagement clubManagement;

    public UserInterface(ClubManagement clubManagement) {
        this.clubManagement = clubManagement;
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput;
            try {
                userInput = Integer.parseInt(IO.readln("\nVælg bruger: \n" +
                        "1. Ledelse\n" +
                        "2. Kasserer\n" +
                        "3. Træner\n" +
                        "4. Medlem\n" +
                        "0. Afslut \n"));
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
                continue;
            }
            switch (userInput) {
                case 1 -> managementMenu();
                case 2 -> financeMenu();
                case 3 -> trainerMenu();
                case 4 -> memberMenu();
                case 0 -> isRunning = false;
                default -> IO.println("Ugyldigt valg. Prøv igen");
            }
        }
    }

    private void managementMenu() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput;

            try {
                userInput = Integer.parseInt(IO.readln("\nIndtast dit valg: \n" +
                        "1. Se alle medlemmer \n" +
                        "0. Tilbage til brugermenu \n"));
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
                continue;
            }
            switch (userInput) {
                case 1 -> showMembers();
                case 0 -> {
                    return;
                }
                default -> IO.println("Ugyldigt valg. Prøv igen.");
            }
        }

    }

    private void financeMenu() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput;

            try {
                userInput = Integer.parseInt(IO.readln("\nIndtast dit valg: \n" +
                        "1. Se regninger\n" +
                        "2. Medlemmer i restance\n" +
                        "3. Se betalte regninger\n" +
                        "0. Tilbage til brugermenu \n"));
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
                continue;
            }
            switch (userInput) {
                case 1 -> showFees();
                case 2 -> showOverdueFees();
                case 3 -> showMemberFeesPayment();
                case 0 -> {
                    return;
                }
                default -> IO.println("Ugyldigt valg. Prøv igen.");
            }
        }

    }

    private void trainerMenu() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput;
            try {
                userInput = Integer.parseInt(IO.readln("\nIndtast dit valg:\n" +
                        "1. Tilføj disciplin til svømmer\n" +
                        "2. Se junior holdet\n" +
                        "3. Se senior holdet\n" +
                        "4. Registrer ny træningstid\n" +
                        "5. Se bedste træningstider\n" +
                        "6. Se bedste stævnetider\n" +
                        "0. Tilbage til brugermenu\n"));
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
                continue;
            }
            switch (userInput) {
                case 1 -> addDisciplin();
                case 2 -> showJuniorTeam();
                case 3 -> showSeniorTeam();
                case 4 -> createResultTime();
                case 5 -> showTopTrainingSwimmers();
                case 6 -> showTopCompetitionSwimmers();
                case 0 -> {
                    return;
                }
                default -> IO.println("ugyldigt valg. Prøv igen.");
            }
        }

    }

    private void memberMenu() {
        boolean isRunning = true;
        while (isRunning) {
            int userInput;
            try {
                userInput = Integer.parseInt(IO.readln("\nIndtast dit valg:\n" +
                        "1. Opret ny svømmer\n" +
                        "2. Ændrer medlemsstatus (aktiv/passiv)\n" +
                        "3. Betal kontigent\n" +
                        "0. Tilbage til brugermenu \n"));
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
                continue;
            }
            switch (userInput) {
                case 1 -> createMember();
                case 2 -> changeMembershipStatus();
                case 3 -> payFee();
                case 0 -> {
                    return;
                }
                default -> IO.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    public void payFee() {
        try {
            int memberId = Integer.parseInt(IO.readln("Angiv medlemsnummer: "));
            Member member = clubManagement.findMemberByMemberId(memberId);
            if (member == null) {
                IO.println("Der findes ikke et medlem med dette medlemsnummer.");
                return;
            }

            List<MemberFee> unpaidFees = clubManagement.getUnpaidFees(memberId);
            if (unpaidFees.isEmpty()) {
                IO.println("Du har ingen ubetalte regninger.");
                return;
            }

            for (MemberFee memberFee : unpaidFees) {
                IO.println(memberFee);
                String userAnswer = IO.readln("Vil du betale denne regning? (ja/nej): ");
                if (userAnswer.equalsIgnoreCase("ja")) {
                    clubManagement.registerPayment(memberFee);
                    IO.println("Regningen er betalt.");
                }
            }
        } catch (NumberFormatException e) {
            IO.println("Medlems Id skal være et tal");
        }
    }

    public void showFees() {
        if (clubManagement.getFees().isEmpty()) {
            IO.println("Der er ingen regninger.");
            return;
        }
        for (MemberFee memberFee : clubManagement.getFees()) {
            IO.println(memberFee);
        }
    }

    public void showOverdueFees() {
        List<MemberFee> overdueFees = clubManagement.getOverdueFees(LocalDate.now());

        if (overdueFees.isEmpty()) {
            IO.println("Der er ingen medlemmer i restance.");
            return;
        }
        for (MemberFee memberFee : overdueFees) {
            IO.println(memberFee);
        }
    }

    public void showMemberFeesPayment() {
        List<MemberFee> feesPayment = clubManagement.getMemberFeesPayment(LocalDate.now());

        if (feesPayment.isEmpty()) {
            IO.println("Ingen betalte regninger.");
            return;
        }
        for (MemberFee memberFee : feesPayment) {
            IO.println(memberFee);
        }
    }

    private void createMember() {
        String name = IO.readln("Indtast navn: ");
        int memberId = clubManagement.getNewMemberId();
        IO.println("Dit medlems ID er: " + memberId);
        LocalDate birthday;

        while (true) {
            try {
                birthday = LocalDate.parse(IO.readln("Indtast fødselsdato (YYYY-MM-DD): "));

                if (birthday.isAfter(LocalDate.now())) {
                    IO.println("Fødselsdatoen kan ikke være ude i fremtiden.");
                    continue;
                }
                break;
            } catch (DateTimeParseException e) {
                IO.println("Ugyldig dato. Husk at brug formatet YYYY-MM-DD");
            }
        }
        MembershipType membershipType = chooseMembershipStatus();
        FitnessSwimmer member = new FitnessSwimmer(name, memberId, birthday, membershipType, null);
        member.setSwimmerAgeType();

        clubManagement.addMember(member);
        clubManagement.createFee(member);
        IO.println("Medlemmet " + member.getName() + ": er oprettet og fået første regning.");

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
        int memberId;

        while (true) {
            try {
                memberId = Integer.parseInt(IO.readln("Indtast medlems id: "));
                break;
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
            }
        }
        Member member = clubManagement.findMemberByMemberId(memberId);

        if (member == null) {
            IO.println("Der findes intet medlem med dette medlems id.");
            return;
        }

        MembershipType membershipType = chooseMembershipStatus();
        member.setMembershipType(membershipType);
        IO.println("Medlemsstatus er ændret for: " + member.getName());
    }

    public void showMembers() {
        if (clubManagement.getMembers().isEmpty()) {
            IO.println("Der er ingen medlemmer.");
            return;
        }
        for (Member member : clubManagement.getMembers()) {
            IO.println(member);
        }
    }

    public void addDisciplin() {
        CompetetiveSwimmer competetiveSwimmer;

        while (true) {
            try {
                int memberId = Integer.parseInt(IO.readln("Indtast medlems id: "));
                clubManagement.fitnessSwimmerToCompetetiveSwimmer(memberId);
                competetiveSwimmer = clubManagement.findCompetetiveSwimmerByMemberId(memberId);
                if (competetiveSwimmer == null) {
                    IO.println("Der findes intet medlem med dette medlems id.");
                    return;
                }
                break;
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
            }
        }

        Disciplin disciplin = chooseDisciplin();

        boolean added = competetiveSwimmer.addDisciplin(disciplin);
        if (added) {
            IO.println("Disciplinen er tilføjet.");
        } else {
            IO.println("Svømmeren har allerede denne disciplin.");
        }
    }

    public void createResultTime() {
        int memberId = Integer.parseInt(IO.readln("Angiv medlemsnummer: "));
        CompetetiveSwimmer swimmer = clubManagement.findCompetetiveSwimmerByMemberId(memberId);
        if (swimmer == null) {
            IO.println("Medlem ikke fundet.");
            return;
        }

        Disciplin disciplin = chooseDisciplin();
        int choiceTrainingOrCompetition = Integer.parseInt(IO.readln("Tryk 1 for træningsresultat eller 2 for stævneresultat"));
        if (choiceTrainingOrCompetition == 1) {
            if (swimmer.getDisciplins().contains(disciplin)) {
                String resultTime = IO.readln("Angiv tid for trnæningsresultatet i formatet: xx:xx:xx: ");
                LocalDate dateOfResult = LocalDate.parse(IO.readln("Angiv dato for træningsresultatet i formatet: yyyy-mm-dd:  "));

                ResultTime resultTimeConverted = clubManagement.convertTime(resultTime);
                TrainingResult trainingResult = new TrainingResult(disciplin, resultTimeConverted, dateOfResult);
                clubManagement.assignTrainingResult(swimmer, trainingResult);
                IO.println("Træningsresultat tilføjet!");
            } else {
                IO.println("Svømmeren er ikke registreret i denne disciplin. Tilføj discplininen i hovedmenuen, for at registrere træningsresultater");
            }

        }
        else if (choiceTrainingOrCompetition == 2) {
            if (swimmer.getDisciplins().contains(disciplin)) {
                String resultTime = IO.readln("Angiv tid for stævneresultatet i formatet: xx:xx:xx: ");
                LocalDate dateOfResult = LocalDate.parse(IO.readln("Angiv dato for stævneresultatet i formatet: yyyy-mm-dd:  "));
                int placement = Integer.parseInt(IO.readln("Angiv placering."));

                ResultTime resultTimeConverted = clubManagement.convertTime(resultTime);
                CompetitionResult competitionResult = new CompetitionResult(disciplin, resultTimeConverted, dateOfResult, placement);
                clubManagement.assignCompetitionResult(swimmer, competitionResult);
                IO.println("Stævnesresultat tilføjet!");
            } else {
                IO.println("Svømmeren er ikke registreret i denne disciplin. Tilføj discplininen i hovedmenuen, for at registrere stævnesresultater");
            }
        } else {
            IO.println("Ugyldigt input");
        }
    }

    public Disciplin chooseDisciplin() {
        while (true) {
            int userInput;
            try {
                userInput = Integer.parseInt(IO.readln("Vælg disciplin:\n" +
                        "1. Butterfly\n" +
                        "2. Crawl\n" +
                        "3. Rygcrawl\n" +
                        "4. Brystsvømning\n"));
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
                continue;
            }

            switch (userInput) {
                case 1 -> {
                    return Disciplin.BUTTERFLY;
                }
                case 2 -> {
                    return Disciplin.CRAWL;
                }
                case 3 -> {
                    return Disciplin.BACK_CRAWL;
                }
                case 4 -> {
                    return Disciplin.BREASTSTROKE;
                }
                default -> IO.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    public Team chooseTeam() {
        while (true) {
            int userInput;

            try {
                userInput = Integer.parseInt(IO.readln("Vælg hold: \n" +
                        "1. Juniorhold \n" +
                        "2. Seniorhold\n"));
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
                continue;
            }
            switch (userInput) {
                case 1 -> {
                    return clubManagement.getJuniorTeam();
                }
                case 2 -> {
                    return clubManagement.getSeniorTeam();
                }
                default -> IO.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    public void showJuniorTeam() {
        Team team = clubManagement.getJuniorTeam();
        IO.println(team.getTeamName());

        if (team.getCompetetiveSwimmers().isEmpty()) {
            IO.println("Der er ingen svømmere på junior holdet");
            return;
        }

        for (CompetetiveSwimmer competetiveSwimmer : team.getCompetetiveSwimmers()) {
            IO.println(competetiveSwimmer);
        }
    }

    public void showSeniorTeam() {
        Team team = clubManagement.getSeniorTeam();
        IO.println(team.getTeamName());

        if (team.getCompetetiveSwimmers().isEmpty()) {
            IO.println("Der er ingen svømmere på senior holdet");
            return;
        }

        for (CompetetiveSwimmer competetiveSwimmer : team.getCompetetiveSwimmers()) {
            IO.println(competetiveSwimmer);
        }
    }

    public int chooseNumberOfSwimmers() {
        while (true) {
            try {
                int numberOfSwimmers = Integer.parseInt(IO.readln("Hvor mange svømmere vil du se?"));

                if (numberOfSwimmers > 0) {
                    return numberOfSwimmers;
                }
                IO.println("Antallet skal være større end 0.");
            } catch (NumberFormatException e) {
                IO.println("Ugyldigt input. Indtast et tal.");
            }
        }
    }

    public void showTopTrainingSwimmers() {
        Team team = chooseTeam();
        Disciplin disciplin = chooseDisciplin();
        int numberOfSwimmers = chooseNumberOfSwimmers();

        List<CompetetiveSwimmer> competetiveSwimmers = clubManagement.getTopTrainingSwimmers(team, disciplin, numberOfSwimmers);

        if (competetiveSwimmers.isEmpty()) {
            IO.println("Der er ingen træningstider i denne disciplin på holdet.");
            return;
        }
        IO.println(team.getTeamName() + " - " + disciplin);

        int rank = 1;
        for (CompetetiveSwimmer competetiveSwimmer : competetiveSwimmers) {
            IO.println(rank + ". " + competetiveSwimmer.getName() + " (ID " + competetiveSwimmer.getMemberId() + ")");
            IO.println(competetiveSwimmer.getBestTrainingTime(disciplin));
            rank++;
        }
    }

    public void showTopCompetitionSwimmers() {
        Team team = chooseTeam();
        Disciplin disciplin = chooseDisciplin();
        int numberOfSwimmers = chooseNumberOfSwimmers();

        List<CompetetiveSwimmer> competetiveSwimmers = clubManagement.getTopCompetitionSwimmers(team, disciplin, numberOfSwimmers);

        if (competetiveSwimmers.isEmpty()) {
            IO.println("Der er ingen konkurrencetider for denne disciplin på holdet.");
            return;
        }
        IO.println(team.getTeamName() + " - " + disciplin);

        int rank = 1;
        for (CompetetiveSwimmer competetiveSwimmer : competetiveSwimmers) {
            IO.println(rank + ". " + competetiveSwimmer.getName() + " (ID " + competetiveSwimmer.getMemberId() + ")");
            IO.println(competetiveSwimmer.getBestCompetitionTime(disciplin));
            rank++;
        }
    }
}

