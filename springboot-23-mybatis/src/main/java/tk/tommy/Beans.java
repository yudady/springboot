package tk.tommy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Beans {

	public static void main(String[] args) throws Exception {
		System.out.println(new SdpChild().getRegionSports());
		System.out.println("--------");
		System.out.println(new DgtChild().getRegionSports());
		System.out.println("--------");
		System.out.println(new BrChild().getRegionSports());
	}

	@Data
	@Slf4j
	public static abstract class BaseRegionSport {

		protected String regionNum;
		protected String sportId;

	}

	@NoArgsConstructor
	public static class RegionSportDto extends SdpRegionSport {
	}

	@NoArgsConstructor
	public static class SdpRegionSport extends BaseRegionSport {
	}

	@NoArgsConstructor
	public static class DgtRegionSport extends BaseRegionSport {
	}
	@NoArgsConstructor
	public static class BrRegionSport extends BaseRegionSport {
	}

}

interface RegionSportInterface {

	List<Beans.RegionSportDto> getRegionSports() throws Exception;
}

class Parent {

	List<Beans.RegionSportDto> getRegionSportsConvert(
		Function<Map<String, Object>, List<? extends Beans.BaseRegionSport>> function,
		Map<String, Object> map) throws Exception {

		List<? extends Beans.BaseRegionSport> apply = function.apply(map);

		return apply.stream().map(obj -> {
			Beans.RegionSportDto regionSport = new Beans.RegionSportDto();
			regionSport.setRegionNum(obj.getRegionNum());
			regionSport.setSportId(obj.getSportId());
			return regionSport;
		}).collect(Collectors.toList());

	}
}

class SdpChild extends Parent implements RegionSportInterface {

	public List<Beans.RegionSportDto> getRegionSports() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("regionNum", "SdpRegionSport.regionNum");
		map.put("sportId", "SdpRegionSport.sportId");
		return getRegionSportsConvert(this::findByData, map);

	}

	public List<Beans.SdpRegionSport> findByData(Map<String, Object> paramMap) {
		List<Beans.SdpRegionSport> regionSports = new ArrayList<>();
		Beans.SdpRegionSport bean = new Beans.SdpRegionSport();
		bean.setRegionNum(paramMap.get("regionNum").toString());
		bean.setSportId(paramMap.get("sportId").toString());
		regionSports.add(bean);
		System.out.println("SdpRegionSport");
		return regionSports;
	}
}

class DgtChild extends Parent implements RegionSportInterface {

	public List<Beans.RegionSportDto> getRegionSports() throws Exception {
		Map<String, Object> map = new HashMap<>();

		return getRegionSportsConvert((Map<String, Object> paramMap) -> {
			List<Beans.DgtRegionSport> brMatchMarketArgument = new ArrayList<>();
			Beans.DgtRegionSport bean = new Beans.DgtRegionSport();
			bean.setRegionNum("DgtRegionSport.regionNum");
			bean.setSportId("DgtRegionSport.sportId");
			brMatchMarketArgument.add(bean);

			System.out.println("DgtRegionSport");
			return brMatchMarketArgument;
		}, map);

	}
}
class BrChild extends Parent implements RegionSportInterface {

	public List<Beans.RegionSportDto> getRegionSports() throws Exception {
		Map<String, Object> map = new HashMap<>();

		return getRegionSportsConvert((Map<String, Object> paramMap) -> {
			List<Beans.BrRegionSport> brMatchMarketArgument = new ArrayList<>();
			Beans.BrRegionSport bean = new Beans.BrRegionSport();
			bean.setRegionNum("BrRegionSport.regionNum");
			bean.setSportId("BrRegionSport.sportId");
			brMatchMarketArgument.add(bean);

			System.out.println("BrRegionSport");
			return brMatchMarketArgument;
		}, map);

	}
}
