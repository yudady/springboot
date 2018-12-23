package com.tommy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsMatchCompetitor implements Serializable {

    private Long competitorId;
    private String role;
    private Integer score;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private Date createdTime;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
//    private Date updatedTime;


}
