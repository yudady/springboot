package com.betmatrix.theonex.dao.br.model;

import java.util.Date;

public class BrMatchMarketArgument {
    private Integer matchMarketArgumentId;

    private Long matchId;

    private Long marketId;

    private Integer marketTypeId;

    private Integer eventTypeId;

    private String argument;

    private Byte isPrime;

    private Byte isActive;

    private Byte isVisible;

    private Byte isCashoutable;

    private Date updatedTime;

    public Integer getMatchMarketArgumentId() {
        return matchMarketArgumentId;
    }

    public void setMatchMarketArgumentId(Integer matchMarketArgumentId) {
        this.matchMarketArgumentId = matchMarketArgumentId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Integer getMarketTypeId() {
        return marketTypeId;
    }

    public void setMarketTypeId(Integer marketTypeId) {
        this.marketTypeId = marketTypeId;
    }

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument == null ? null : argument.trim();
    }

    public Byte getIsPrime() {
        return isPrime;
    }

    public void setIsPrime(Byte isPrime) {
        this.isPrime = isPrime;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Byte getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Byte isVisible) {
        this.isVisible = isVisible;
    }

    public Byte getIsCashoutable() {
        return isCashoutable;
    }

    public void setIsCashoutable(Byte isCashoutable) {
        this.isCashoutable = isCashoutable;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}