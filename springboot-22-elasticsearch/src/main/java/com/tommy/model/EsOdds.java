package com.tommy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsOdds implements Serializable {
    private Byte partnerId;
    private Integer optionNum;
    private BigDecimal factor;
    private Byte isActive;
    private Integer outComeCode;
    private Boolean isForLiveONLY;
//    private String signature;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private Date updatedTime;

}
