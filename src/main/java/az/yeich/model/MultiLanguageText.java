package az.yeich.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MultiLanguageText {
    private String language;
    private String text;

}
