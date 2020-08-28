package codes.purple.chabarok.dtos;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderedTableDTO {
    private Date orderedDate;
    private Date orderedFromTime;
    private Date orderedToTime;
    private Long table;
    private Long peoples;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
