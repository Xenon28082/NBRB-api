package com.xenon.nbrbapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

    @Id
    @JsonProperty("Cur_ID ")
    private Integer curIdl;
    @JsonProperty("Cur_ParentID ")
    private Integer curParentId;
    @JsonProperty("Cur_Code ")
    private String curCode;
    @JsonProperty("Cur_Abbreviation ")
    private String curAbbreviation;
    @JsonProperty("Cur_Name ")
    private String curName;
    @JsonProperty("Cur_Name_Eng ")
    private String curNameEng;
    @JsonProperty("Cur_Scale ")
    private Integer curScale;
    @JsonProperty("Cur_Periodicity ")
    private Integer curPeriodicity;

}
