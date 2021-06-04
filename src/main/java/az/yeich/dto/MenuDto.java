package az.yeich.dto;

import az.yeich.model.MenuDetail;
import az.yeich.model.MultiLanguageText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private String id;
    private String title;
    private String image;
}
