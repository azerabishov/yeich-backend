package az.yeich.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class MenuDetail {
    private List<MultiLanguageText> title;
    private List<MultiLanguageText> description;
    private Integer price;
    private String image;


}
