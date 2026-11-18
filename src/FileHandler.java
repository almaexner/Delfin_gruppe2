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

    //Members
    public void saveMember(List<Member> memberList) throws IOException{
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (Member member : memberList) {
                String line = mapper.memberToCsv(member);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
    }
    public List<Member> loadMembers() throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            List<Member> members = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                members.add(mapper.csvToMember(line));
            }
            return members;
        }
    }

    //Fitness swimmer
    public void saveFitnessSwimmer(List<FitnessSwimmer> fitnessSwimmers) throws IOException{
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (FitnessSwimmer fitnessSwimmer : fitnessSwimmers){
                writer.write(mapper.fitnessSwimmerToCsv(fitnessSwimmer));
                writer.newLine();
            }
        }
    }
    public List<FitnessSwimmer> loadFitnessSwimmer() throws IOException{
        try(BufferedReader reader = Files.newBufferedReader(path)){
            List<FitnessSwimmer> fitnessSwimmers = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null){
                fitnessSwimmers.add(mapper.csvToFitnessSwimmer(line));

            }
            return fitnessSwimmers;
        }
    }

    //Competetive Swimmer
    public void saveCompetetiveSwimmer(List<CompetetiveSwimmer> competetiveSwimmers) throws IOException{
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for (CompetetiveSwimmer competetiveSwimmer : competetiveSwimmers){
                writer.write(mapper.competetiveSwimmertToCsv(competetiveSwimmer));
                writer.newLine();
            }
        }
    }
    public List<CompetetiveSwimmer> loadCompetetiveSwimmers() throws IOException{
        try(BufferedReader reader = Files.newBufferedReader(path)){
            List<CompetetiveSwimmer> competetiveSwimmers = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null){
                competetiveSwimmers.add(mapper.CsvCompeteiveSwimmer(line));
            }
            return competetiveSwimmers;
        }
    }
}
