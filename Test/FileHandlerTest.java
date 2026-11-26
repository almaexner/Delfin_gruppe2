import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    @TempDir
            Path tempDir;
    ClubManagement clubManagement;
    FileHandler fileHandler;
    List<Member> members;
    List<FitnessSwimmer> fitnessSwimmers;
    List<CompetetiveSwimmer> competetiveSwimmers;
    Path path;

    @BeforeEach
    public void setup(){
        //Arrange
        path = tempDir.resolve("members.csv");
        clubManagement = new ClubManagement();
        fileHandler = new FileHandler(path, new CsvMapper());
        members = List.of(
                new FitnessSwimmer("Jørgen Leth", 1, LocalDate.of(2000,1,1), MembershipType.ACTIVE, SwimmerAgeType.JUNIOR),
                new FitnessSwimmer("Jørgen Leth 2", 2, LocalDate.of(1999,1,1), MembershipType.ACTIVE, SwimmerAgeType.SENIOR),
                new FitnessSwimmer("Jørgen Leth 3", 3, LocalDate.of(2002,1,1), MembershipType.PASSIVE, SwimmerAgeType.SENIOR)
        );
    }
    @Test
    public void testSaveMembersToFile() throws IOException {
        //Act

        fileHandler.saveFitnessSwimmer(fitnessSwimmers);

        //Assert
        assertTrue(Files.exists(path));
        assertTrue(Files.size(path) > 0);
    }
    @Test
    public void testLoadMembersFromFile() throws IOException {
        //Act
        fileHandler.saveFitnessSwimmer(fitnessSwimmers);
        List<FitnessSwimmer> membersFromFile = fileHandler.loadFitnessSwimmer();

        //Assert
        assertTrue(Files.exists(path));
        assertFalse(membersFromFile.isEmpty());
    }
    @Test
    public void testLoadFitnessSwimmerFromFile() throws IOException {
        //Arrange
        fitnessSwimmers = List.of(
                new FitnessSwimmer("Jørgen Leth", 1, LocalDate.of(2000,1,1), MembershipType.ACTIVE, SwimmerAgeType.JUNIOR),
                new FitnessSwimmer("Jørgen Leth 2", 2, LocalDate.of(1999,1,1), MembershipType.ACTIVE, SwimmerAgeType.SENIOR),
                new FitnessSwimmer("Jørgen Leth 3", 3, LocalDate.of(2002,1,1), MembershipType.PASSIVE, SwimmerAgeType.SENIOR)
        );
        //Act
        fileHandler.saveFitnessSwimmer(fitnessSwimmers);
        List<FitnessSwimmer> fitnessSwimmersFromFile = fileHandler.loadFitnessSwimmer();

        //Assert
        assertTrue(Files.exists(path));
        assertFalse(fitnessSwimmersFromFile.isEmpty());
    }
    @Test
    public void testSaveFitnessSwimmerToFile() throws IOException {
        //Arrange
        fitnessSwimmers = List.of(
                new FitnessSwimmer("Jørgen Leth", 1, LocalDate.of(2000,1,1), MembershipType.ACTIVE, SwimmerAgeType.JUNIOR),
                new FitnessSwimmer("Jørgen Leth 2", 2, LocalDate.of(1999,1,1), MembershipType.ACTIVE, SwimmerAgeType.SENIOR),
                new FitnessSwimmer("Jørgen Leth 3", 3, LocalDate.of(2002,1,1), MembershipType.PASSIVE, SwimmerAgeType.SENIOR)
        );
        //Act
        fileHandler.saveFitnessSwimmer(fitnessSwimmers);

        //Assert
        assertTrue(Files.exists(path));
        assertFalse(Files.size(path) < 0);
    }
    @Test
    public void testLoadCompetetiveSwimmerFromFile() throws IOException{
        //Arrange
        competetiveSwimmers = List.of(
                new CompetetiveSwimmer("Jørgen Leth", 1, LocalDate.of(2000,1,1), MembershipType.ACTIVE, SwimmerAgeType.JUNIOR),
                new CompetetiveSwimmer("Jørgen Leth 2", 2, LocalDate.of(1999,1,1), MembershipType.ACTIVE, SwimmerAgeType.SENIOR),
                new CompetetiveSwimmer("Jørgen Leth 3", 3, LocalDate.of(2002,1,1), MembershipType.PASSIVE, SwimmerAgeType.SENIOR)
        );
        //Act
        fileHandler.saveCompetetiveSwimmer(competetiveSwimmers);
        List<CompetetiveSwimmer> competetiveSwimmersFromFile = fileHandler.loadCompetetiveSwimmers();

        //Assert
        assertTrue(Files.exists(path));
        assertFalse(competetiveSwimmersFromFile.isEmpty());
    }
}
