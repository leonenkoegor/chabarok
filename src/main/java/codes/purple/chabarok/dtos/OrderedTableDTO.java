package codes.purple.chabarok.dtos;

import codes.purple.chabarok.models.OrderedTable;
import lombok.Data;

import java.sql.Date;

@Data
public class OrderedTableDTO {
    private Date orderedDate;
    private Date orderedFromTime;
    private Date orderedToTime;
    private Long peoples;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public OrderedTableDTO(OrderedTable orderedTable) {
        this.orderedDate = orderedTable.getOrderedDate();
        this.orderedFromTime = orderedTable.getOrderedFromTime();
        this.orderedToTime = orderedTable.getOrderedToTime();
        this.peoples = orderedTable.getPeoples();
        this.firstName = orderedTable.getFirstName();
        this.lastName = orderedTable.getLastName();
        this.phoneNumber = orderedTable.getPhoneNumber();
    }
}
