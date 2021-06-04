package az.yeich.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDto {
    private String[] categories;
    private String[] features;
    private String[] cuisines;
    private String metro;
    private String searchKey;
}
