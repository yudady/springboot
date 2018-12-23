package com.tommy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "bridx", type = "matchinfo", shards = 1, replicas = 0)
@Data
public class EsMatchInfoDoc implements Serializable {

	@Id
	private Long matchId;
	private Byte isInLive;
	private Long leagueId;
	private String matchTitle;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	@Field(type = FieldType.Date, format = DateFormat.basic_date_time)
	private Date matchTime;
	private BigDecimal passedMinutes;
	private Integer status;
	private Integer priority;
	private Byte isActive;
	private Byte isVisible;
	private String currentScore;
	private String periodSetScore;
	private Integer LSFProviderId;
	private Long LSFMatchId;

	//
	private String leagueName;
	private String regionFullName;
	private String leagueGroupName;
	private Long sportId;
	private String sportName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	@Field(type = FieldType.Date, format = DateFormat.basic_date_time)
	private Date createdTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	@Field(type = FieldType.Date, format = DateFormat.basic_date_time)
	private Date updatedTime;
	private String matchIdXRef;



    @Field(type = FieldType.Nested)
    private List<EsMatchCompetitor> esMatchCompetitors;

    @Field(type = FieldType.Nested)
    private List<EsMatchMarketArgument> esMatchMarketArguments;
}
