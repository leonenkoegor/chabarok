package codes.purple.chabarok.models;

import codes.purple.chabarok.dtos.OrderedTableDTO;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orderedTables")
@Data
public class OrderedTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date orderedDate;
    @Temporal(TemporalType.TIME)
    private Date orderedFromTime;
    @Temporal(TemporalType.TIME)
    private Date orderedToTime;
    private Long peoples;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public OrderedTable(OrderedTableDTO orderedTableDTO) {
        this.orderedDate = orderedTableDTO.getOrderedDate();
        this.orderedFromTime = orderedTableDTO.getOrderedFromTime();
        this.orderedToTime = orderedTableDTO.getOrderedToTime();
        this.peoples = orderedTableDTO.getPeoples();
        this.firstName = orderedTableDTO.getFirstName();
        this.lastName = orderedTableDTO.getLastName();
        this.phoneNumber = orderedTableDTO.getPhoneNumber();
    }

    @Tolerate
    public OrderedTable() {
    }
}
