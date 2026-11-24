import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private Path path;
    private CsvMapper mapper;

    public FileHandler(Path path, CsvMapper mapper) {
        this.path = path;
        this.mapper = mapper;
    }

    //Fitness swimmer
    public void saveFitnessSwimmer(List<FitnessSwimmer> fitnessSwimmers) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (FitnessSwimmer fitnessSwimmer : fitnessSwimmers) {
                writer.write(mapper.fitnessSwimmerToCsv(fitnessSwimmer));
                writer.newLine();
            }
        }
    }

    public List<FitnessSwimmer> loadFitnessSwimmer() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<FitnessSwimmer> fitnessSwimmers = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                fitnessSwimmers.add(mapper.csvToFitnessSwimmer(line));

            }
            return fitnessSwimmers;
        }
    }

    //Competetive Swimmer
    public void saveCompetetiveSwimmer(List<CompetetiveSwimmer> competetiveSwimmers) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (CompetetiveSwimmer competetiveSwimmer : competetiveSwimmers) {
                writer.write(mapper.competetiveSwimmertToCsv(competetiveSwimmer));
                writer.newLine();
            }
        }
    }

    public List<CompetetiveSwimmer> loadCompetetiveSwimmers() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<CompetetiveSwimmer> competetiveSwimmers = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                competetiveSwimmers.add(mapper.csvToCompeteiveSwimmer(line));
            }
            return competetiveSwimmers;
        }
    }

    public void saveMemberFee(List<MemberFee> fees) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (MemberFee memberFee : fees) {
                writer.write(mapper.memberFeeToCsv(memberFee));
                writer.newLine();
            }
        }
    }

    public List<MemberFee> loadMemberFees(ClubManagement clubManagement) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<MemberFee> fees = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                MemberFee memberFee = mapper.csvToMemberFee(line, clubManagement);

                if (memberFee != null) {
                    fees.add(memberFee);
                }
            }
            return fees;
        }
    }
}
