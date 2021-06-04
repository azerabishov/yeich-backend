package az.yeich.model;


import az.yeich.enums.PlaceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms_tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTable {
    @Id
    private String id;
    private String name;
    private String feature;
    private String image;
    private String maxNumberOfPerson;
    private String deposite;
    private PlaceTypeEnum type;
}
