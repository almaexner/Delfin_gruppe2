import java.time.LocalDate;

public class CsvMapper {

    private String separator = ",";

    public Member csvToMember(String line) {
        String[] tokens = line.split(separator, -1);
        String name = tokens[0];
        int memberId = Integer.parseInt(tokens[1]);
        LocalDate dateOfBirth = LocalDate.parse(tokens[2]);
        MembershipType membershipType = MembershipType.valueOf(tokens[3]);
        SwimmerAgeType swimmerAgeType = SwimmerAgeType.valueOf(tokens[4]);

        return new Member(name, memberId, dateOfBirth, membershipType, swimmerAgeType);
    }

        public String memberToCsv(Member member) {
            return  member.getName()
                    + separator + member.getMemberId()
                    + separator + member.getDateOfBirth()
                    + separator + member.getMembershipType()
                    + separator + member.getSwimmerAgeType();
        }

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
        return  fitnessSwimmer.getName()
                + separator + fitnessSwimmer.getName()
                + separator + fitnessSwimmer.getMemberId()
                + separator + fitnessSwimmer.getDateOfBirth()
                + separator + fitnessSwimmer.getMembershipType();
        }

        public CompetetiveSwimmer CsvCompeteiveSwimmer(String line) {
        String[] tokens = line.split(separator, 1);
        String name = tokens[0];
        int memberId = Integer.parseInt(tokens[1]);
        LocalDate dateOfBirth = LocalDate.parse(tokens[2]);
        MembershipType membershipType = MembershipType.valueOf(tokens[3]);
        SwimmerAgeType swimmerAgeType = SwimmerAgeType.valueOf(tokens[4]);
        Disciplin disciplin = Disciplin.valueOf(tokens[5]);
        return new CompetetiveSwimmer(name, memberId, dateOfBirth, membershipType, swimmerAgeType, disciplin);

        }

        public String competetiveSwimmertToCsv(CompetetiveSwimmer competetiveSwimmer) {
        return competetiveSwimmer.getName()
                + separator + competetiveSwimmer.getMemberId()
                + separator + competetiveSwimmer.getDateOfBirth()
                + separator + competetiveSwimmer.getMembershipType()
                + separator + competetiveSwimmer.getDisciplin();
        }
}
