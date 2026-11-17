import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
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
    Path path;

    @BeforeEach
    public void setup(){
        //Arrange
        path = tempDir.resolve("members.csv");
        clubManagement = new ClubManagement();
        fileHandler = new FileHandler(path, new CsvMapper());
        members = List.of(
                new Member("Jørgen Leth", 1, LocalDate.of(2000,1,1), MembershipType.ACTIVE, SwimmerAgeType.JUNIOR),
                new Member("Jørgen Leth 2", 2, LocalDate.of(1999,1,1), MembershipType.ACTIVE, SwimmerAgeType.SENIOR),
                new Member("Jørgen Leth 3", 3, LocalDate.of(2002,1,1), MembershipType.PASSIVE, SwimmerAgeType.SENIOR)
        );
    }
    @Test
    public void testSaveMembersToFile() throws IOException {
        //Act
        fileHandler.saveMember(members);

        //Assert
        assertTrue(Files.exists(path));
        assertTrue(Files.size(path) > 0);
    }
    @Test
    public void testLoadMembersFromFile() throws IOException {
        //Act
        fileHandler.saveMember(members);
        List<Member> membersFromFile = fileHandler.loadMembers();

        //Assert
        assertTrue(Files.exists(path));
        assertFalse(membersFromFile.isEmpty());
    }
}
