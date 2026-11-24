import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabaseLoader {
    private FileHandler memberFile;
    private FileHandler fitnessSwimmerFile;
    private FileHandler competetiveSwimmerFile;
    private FileHandler memberFeeFile;

    public DatabaseLoader() {
        CsvMapper mapper = new CsvMapper();

        memberFile = new FileHandler(Path.of("members.csv"), mapper);
        fitnessSwimmerFile = new FileHandler(Path.of("fitnessSwimmers.csv"), mapper);
        competetiveSwimmerFile = new FileHandler(Path.of("competetiveSwimmers.csv"), mapper);
        memberFeeFile = new FileHandler(Path.of("memberFees.csv"), mapper);
    }

    public void createFiles() throws IOException {
        createFileIfNotExists(Path.of("members.csv"));
        createFileIfNotExists(Path.of("fitnessSwimmers.csv"));
        createFileIfNotExists(Path.of("competetiveSwimmers.csv"));
        createFileIfNotExists(Path.of("memberFees.csv"));
    }

    public void createFileIfNotExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public void loadData(ClubManagement clubManagement) throws IOException {

//        for (Member member : memberFile.loadMembers()) {
//            member.setSwimmerAgeType(); //Checker ved programstart om medlem har skiftet SwimmerAgeType og retter hvis tilfældet.
//            clubManagement.addMember(member);
//        }

        for (FitnessSwimmer fitnessSwimmer : fitnessSwimmerFile.loadFitnessSwimmer()) {
            clubManagement.addMember(fitnessSwimmer);
        }

        for (CompetetiveSwimmer competetiveSwimmer : competetiveSwimmerFile.loadCompetetiveSwimmers()) {
            clubManagement.addMember(competetiveSwimmer);
        }

        for (MemberFee memberFee : memberFeeFile.loadMemberFees(clubManagement)) {
            clubManagement.addFee(memberFee);
        }
    }

    public void saveData(ClubManagement clubManagement) throws IOException {
        memberFile.saveMember(clubManagement.getMembers());
        fitnessSwimmerFile.saveFitnessSwimmer(clubManagement.getFitnessSwimmers());
        competetiveSwimmerFile.saveCompetetiveSwimmer(clubManagement.getCompetetiveSwimmers());
        memberFeeFile.saveMemberFee(clubManagement.getFees());
    }
}
