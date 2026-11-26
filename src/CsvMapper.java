import java.time.LocalDate;
import java.time.LocalDateTime;

public class CsvMapper {

    private String separator = ",";

    public FitnessSwimmer csvToFitnessSwimmer(String line) {
        String[] tokens = line.split(separator, -1);
        String name = tokens[0];
        int memberId = Integer.parseInt(tokens[1]);
        LocalDate dateOfBirth = LocalDate.parse(tokens[2]);
        MembershipType membershipType = MembershipType.valueOf(tokens[3]);
        SwimmerAgeType swimmerAgeType = SwimmerAgeType.valueOf(tokens[4]);
        return new FitnessSwimmer(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
    }

    public String fitnessSwimmerToCsv(FitnessSwimmer fitnessSwimmer) {
        return fitnessSwimmer.getName()
                + separator + fitnessSwimmer.getMemberId()
                + separator + fitnessSwimmer.getDateOfBirth()
                + separator + fitnessSwimmer.getMembershipType()
                + separator + fitnessSwimmer.getSwimmerAgeType();
    }

    public CompetetiveSwimmer csvToCompeteiveSwimmer(String line) {
        String[] tokens = line.split(separator, 6);
        String name = tokens[0];
        int memberId = Integer.parseInt(tokens[1]);
        LocalDate dateOfBirth = LocalDate.parse(tokens[2]);
        MembershipType membershipType = MembershipType.valueOf(tokens[3]);
        SwimmerAgeType swimmerAgeType = SwimmerAgeType.valueOf(tokens[4]);
        CompetetiveSwimmer competetiveSwimmer = new CompetetiveSwimmer(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
        String disciplins = tokens[5];
        disciplins = disciplins.replace("[", ""); //Erstatter [] med tommefelter
        disciplins = disciplins.replace("]", "");

        if (!disciplins.isEmpty()) {
            for (String disciplinName : disciplins.split(", ")) {
                Disciplin disciplin = Disciplin.valueOf(disciplinName);
                competetiveSwimmer.addDisciplin(disciplin);
            }
        }
        return competetiveSwimmer;
    }

    public String competetiveSwimmertToCsv(CompetetiveSwimmer competetiveSwimmer) {
        return competetiveSwimmer.getName()
                + separator + competetiveSwimmer.getMemberId()
                + separator + competetiveSwimmer.getDateOfBirth()
                + separator + competetiveSwimmer.getMembershipType()
                + separator + competetiveSwimmer.getSwimmerAgeType()
                + separator + competetiveSwimmer.getDisciplins();
    }

    public String memberFeeToCsv(MemberFee memberFee) {
        return memberFee.getMember().getMemberId()
                + separator + memberFee.getFee()
                + separator + memberFee.getDueDate()
                + separator + memberFee.isPaid();
    }

    public MemberFee csvToMemberFee(String line, ClubManagement clubManagement) {
        String[] tokens = line.split(separator, -1);
        int memberId = Integer.parseInt(tokens[0]);
        int fee = Integer.parseInt(tokens[1]);
        LocalDate dueDate = LocalDate.parse(tokens[2]);
        boolean paid = Boolean.parseBoolean(tokens[3]);
        Member member = clubManagement.findMemberByMemberId(memberId); //Vi skal finde et eksisterende medlem
        if (member == null) {
            return null;
        }
        return new MemberFee(member, fee, dueDate, paid);
    }

    public String trainingResultToCsv(CompetetiveSwimmer competetiveSwimmer, TrainingResult trainingResult) {
        return competetiveSwimmer.getMemberId()
                + separator + trainingResult.getDisciplin()
                + separator + trainingResult.getResultTime().toCsv()
                + separator + trainingResult.getResultDate();
    }

    public TrainingResult csvToTrainingResult(String line, ClubManagement clubManagement) {
        String[] tokens = line.split(separator, -1);
        Disciplin disciplin = Disciplin.valueOf(tokens[1]);
        ResultTime resultTime = clubManagement.convertTime(tokens[2]);
        LocalDate resultDate = LocalDate.parse(tokens[3]);

        return new TrainingResult(disciplin, resultTime, resultDate);
    }

    public String competitionResultToCsv(CompetetiveSwimmer competetiveSwimmer, CompetitionResult competitionResult) {
        return competetiveSwimmer.getMemberId()
                + separator + competitionResult.getDisciplin()
                + separator + competitionResult.getResultTime().toCsv()
                + separator + competitionResult.getResultDate()
                + separator + competitionResult.getPlacement();
    }

    public CompetitionResult csvToCompetitionResult(String line, ClubManagement clubManagement) {
        String[] tokens = line.split(separator, -1);

        Disciplin disciplin = Disciplin.valueOf(tokens[1]);
        ResultTime resultTime = clubManagement.convertTime(tokens[2]);
        LocalDate resultDate = LocalDate.parse(tokens[3]);
        int placement = Integer.parseInt(tokens[4]);

        return new CompetitionResult(disciplin, resultTime, resultDate, placement);
    }
}
