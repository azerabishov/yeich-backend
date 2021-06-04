package az.yeich.dto;

import az.yeich.model.MultiLanguageText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDetailDto {
    private String title;
    private String description;
    private Integer price;
    private String image;

}
