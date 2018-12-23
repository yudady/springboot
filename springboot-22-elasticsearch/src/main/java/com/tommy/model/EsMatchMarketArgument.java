package com.tommy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsMatchMarketArgument implements Serializable {

    private Integer matchMarketArgumentId;
    private Long marketId;
    private Integer marketTypeId;
    private Integer eventTypeId;
    private String argument;

    private Byte isPrime;
    private Byte isActive;
    private Byte isVisible;
    private Byte isCashoutable;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private Date updatedTime;


    @Field(type = FieldType.Nested)
    private List<EsOdds> oddses;

}
