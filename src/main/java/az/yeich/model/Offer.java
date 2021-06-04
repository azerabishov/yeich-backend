package az.yeich.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "offers")
@Data
@AllArgsConstructor
@Builder
public class Offer {
    @Id
    private String id;
    private String image;
    private List<MultiLanguageText> desciption;
    private List<MultiLanguageText> subDescription;
    private String offerHours;
    private String restaurantId;
    private String restaurantName;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}
