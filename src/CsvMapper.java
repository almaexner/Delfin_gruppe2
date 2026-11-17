import java.time.LocalDate;

public class CsvMapper {

    private String separator = ",";

    public Member csvToMember(String line) {
        String[] tokens = line.split(separator, -1);
        String name = tokens[0];
        int memberId = Integer.parseInt(tokens[1]);
        LocalDate dateOfBirth = LocalDate.parse(tokens[2]);
        MembershipType membershipType = MembershipType.valueOf(tokens[3]);

        return new Member(name, memberId, dateOfBirth, membershipType);
    }

        public String memberToCsv(Member member) {
            return  member.getName()
                    + separator + member.getMemberId()
                    + separator + member.getDateOfBirth()
                    + separator + member.getMembershipType();
        }
}
