package com.kotu.koreatourism.dto.tour;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourAreaCategoryDTO {

    private int categoryId;
    private String groupId;
    private int categoryLv;
    private String categoryName;
    private int categoryDetailOr;
    private String categoryDetailName;
    private int categoryParentLv;
    private int categoryDetailParentLv;
    private int categoryApiCode;

}
