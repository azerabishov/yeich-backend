package az.yeich.model;

import az.yeich.enums.SponsorTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "sponsors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sponsor {
    private String id;
    private String image;
    private String link;
    private String description;
    private String subDescription;
    private SponsorTypeEnum type;
    private String restaurantId;
    private LocalDateTime expireAt;
}
