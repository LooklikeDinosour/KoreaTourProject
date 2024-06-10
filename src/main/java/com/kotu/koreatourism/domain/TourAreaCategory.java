package com.kotu.koreatourism.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourAreaCategory {

    private int categoryId;
    private String groupId;
    private int categoryLv;
    private String categoryName;
    private int categoryDetailOr;
    private String categoryDetailName;
    private int categoryParentLv;
    private int categoryDetailParentLv;
    private int categoryApiCode;

    //join
    private String categoryNav;
}
