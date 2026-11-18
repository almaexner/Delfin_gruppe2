import java.io.IOException;

public class Main {
    void main() throws IOException {
        ClubManagement clubManagement = new ClubManagement();
        DatabaseLoader databaseLoader = new DatabaseLoader();

        databaseLoader.createFiles();
        databaseLoader.loadData(clubManagement);

        UserInterface ui = new UserInterface(new ClubManagement());
        ui.run();

        databaseLoader.saveData(clubManagement);


    }
}
