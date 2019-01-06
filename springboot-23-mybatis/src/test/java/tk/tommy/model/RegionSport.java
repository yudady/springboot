package tk.tommy.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("rs")
public class RegionSport {
    private Integer regionNum;

    private Long sportId;

    private Integer priority;

    private Date updatedTime;

    private String regionSportXRef;

    public Integer getRegionNum() {
        return regionNum;
    }

    public void setRegionNum(Integer regionNum) {
        this.regionNum = regionNum;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getRegionSportXRef() {
        return regionSportXRef;
    }

    public void setRegionSportXRef(String regionSportXRef) {
        this.regionSportXRef = regionSportXRef == null ? null : regionSportXRef.trim();
    }
}