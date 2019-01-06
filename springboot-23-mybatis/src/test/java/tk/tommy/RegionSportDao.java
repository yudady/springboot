package tk.tommy;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import tk.tommy.model.RegionSport;

public interface RegionSportDao {


    @Select(" SELECT * FROM RegionSport  WHERE regionNum = #{regionNum}")
	List<RegionSport> selectByRegionNum(String regionNum);

	List<RegionSport> selectBySportId(String sportId);
}
