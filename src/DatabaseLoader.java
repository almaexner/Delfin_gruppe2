import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabaseLoader {
    private FileHandler memberFile;
    private FileHandler fitnessSwimmerFile;
    private FileHandler competetiveSwimmerFile;

    public DatabaseLoader() {
        CsvMapper mapper = new CsvMapper();

        memberFile = new FileHandler(Path.of("members.csv"), mapper);
        fitnessSwimmerFile = new FileHandler(Path.of("fitnessSwimmers.csv"), mapper);
        competetiveSwimmerFile = new FileHandler(Path.of("competetiveSwimmers.csv"), mapper);
    }

    public void createFiles() throws IOException {
        createFileIfNotExists(Path.of("members.csv"));
        createFileIfNotExists(Path.of("fitnessSwimmers.csv"));
        createFileIfNotExists(Path.of("competetiveSwimmers.csv"));
    }

    public void createFileIfNotExists(Path path) throws IOException{
        if (!Files.exists(path)) {
            Files.createFile(path);
            IO.println("Oprettede fil: " + path);
        }
    }

    public void loadData(ClubManagement clubManagement) throws IOException {

        for (Member member : memberFile.loadMembers()) {
            clubManagement.addMember(member);
        }

        for (FitnessSwimmer fitnessSwimmer : fitnessSwimmerFile.loadFitnessSwimmer()) {
            clubManagement.addFitnessSwimmers(fitnessSwimmer);
        }

        for (CompetetiveSwimmer competetiveSwimmer : competetiveSwimmerFile.loadCompetetiveSwimmers()) {
            clubManagement.addCompetetiveSwimmer(competetiveSwimmer);
        }
    }

    public void saveData (ClubManagement clubManagement) throws IOException {
        memberFile.saveMember(clubManagement.getMembers());
        fitnessSwimmerFile.saveFitnessSwimmer(clubManagement.getFitnessSwimmers());
        competetiveSwimmerFile.saveCompetetiveSwimmer(clubManagement.getCompetetiveSwimmers());
    }
}
