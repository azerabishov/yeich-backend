package az.yeich.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    private String id;
    private String name;
    private String image;
    @DBRef
    private List<RoomTable> roomTables;
}
