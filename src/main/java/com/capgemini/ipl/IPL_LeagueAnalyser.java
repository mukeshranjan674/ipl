package com.capgemini.ipl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class IPL_LeagueAnalyser {
	private List<Batsman> batsmanList = null;
	private List<Bowler> bowlerList = null;

	public void loadBatsmanData(String BATSMAN_CSV_PATH) throws IPLAnalyserException {
		batsmanList = new CSVBatsman().loadBatsmanList(BATSMAN_CSV_PATH);
	}

	public void loadBowlerData(String BOWLER_CSV_PATH) throws IPLAnalyserException {
		bowlerList = new CSVBowler().loadBowlerList(BOWLER_CSV_PATH);
	}

	/**
	 * UC1 UC5
	 * 
	 * @return
	 */
	public String getBestBattingAveragesCricketers() {
		List<Batsman> sortedBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getAverage).thenComparing(Batsman::getStrikeRate).reversed())
				.collect(Collectors.toList());
		return toJson(sortedBatsmanList);
	}

	/**
	 * UC2 UC4
	 * 
	 * @return
	 */
	public String getBestStrikeRateCricketers() {
		List<Batsman> sortedBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getStrikeRate).thenComparing(Batsman::getBoundries).reversed())
				.collect(Collectors.toList());
		return toJson(sortedBatsmanList);
	}

	/**
	 * UC3
	 * 
	 * @return
	 */
	public String getMaximumBoundriesCricketers() {
		List<Batsman> sortedBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getBoundries).reversed()).collect(Collectors.toList());
		return toJson(sortedBatsmanList);
	}

	/**
	 * UC6
	 * 
	 * @return
	 */
	public String getMaximumRunsCricketers() {
		List<Batsman> sortedBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getRunsScored).thenComparing(Batsman::getAverage).reversed())
				.collect(Collectors.toList());
		return toJson(sortedBatsmanList);
	}

	/**
	 * UC7 UC11
	 * 
	 * @return
	 */
	public String getMaximumBowlingAverageCricketers() {
		List<Bowler> sortedBowlerList = bowlerList.stream().filter(n -> n.getAverage() > 0)
				.sorted(Comparator.comparing(Bowler::getAverage).thenComparing(Bowler::getStrikeRate))
				.collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}

	/**
	 * UC8 UC10
	 * 
	 * @return
	 */
	public String getMaximumBowlingStrikeRatesCricketers() {
		List<Bowler> sortedBowlerList = bowlerList.stream().filter(n -> n.getStrikeRate() > 0)
				.sorted(Comparator.comparing(Bowler::getStrikeRate).thenComparing(Bowler::getHauls))
				.collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}

	/**
	 * UC9
	 * 
	 * @return
	 */
	public String getBestEconomyCricketers() {
		List<Bowler> sortedBowlerList = bowlerList.stream().sorted(Comparator.comparing(Bowler::getStrikeRate))
				.collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}

	/**
	 * UC12
	 * 
	 * @return
	 */
	public String getMaximumWicketsCricketers() {
		List<Bowler> sortedBowlerList = bowlerList.stream().filter(n -> n.getStrikeRate() > 0)
				.sorted(Comparator.comparing(Bowler::getWicketsTaken).thenComparing(Bowler::getAverage).reversed())
				.collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}

	/**
	 * UC13
	 * 
	 * @return
	 */
	
	public List<AllRounder> getAllRoundersList(){
		List<AllRounder> allRounderList = new ArrayList<AllRounder>();
		Map<String, Batsman> batsmanMap = new HashMap<String, Batsman>();
		Map<String, Bowler> bowlerMap = new HashMap<String, Bowler>();
		batsmanList.forEach(n -> batsmanMap.put(n.getName(), n));
		bowlerList.forEach(n -> bowlerMap.put(n.getName(), n));
		
		batsmanMap.forEach((k,v)-> {
						if(bowlerMap.containsKey(k)) {
							AllRounder a = new AllRounder();
							a.setName(k);
							a.setBattingAverage(v.getAverage());
							a.setRunsScored(v.getRunsScored());
							a.setBowlingAverage(bowlerMap.get(k).getAverage());
							allRounderList.add(a);
						}
				});
		allRounderList.forEach(n -> {
						n.setWicketsTaken(bowlerMap.get(n.getName()).getWicketsTaken());
		});
		return allRounderList;
	}
	
	public String getBattingAndBowlingAveragesCricketers() {
		List<AllRounder> allRounderList = getAllRoundersList();
		
		List<AllRounder> battingAllRounderList = allRounderList.stream()
				.filter(n -> n.getBowlingAverage() > 0 & n.getBattingAverage() > 0)
					  .sorted(Comparator.comparing(AllRounder::getBattingAverage).reversed())
					  .collect(Collectors.toList());
		
		List<AllRounder> bowlingAllRounderList = allRounderList.stream()
				.filter(n -> n.getBowlingAverage() > 0 & n.getBattingAverage() > 0)
				  .sorted(Comparator.comparing(AllRounder::getBowlingAverage))
				  .collect(Collectors.toList());
		List<AllRounder> sortedAllRounderList = new ArrayList<>();
		sortedAllRounderList.add(battingAllRounderList.get(0)); 
		sortedAllRounderList.add(bowlingAllRounderList.get(0));
		return toJson(sortedAllRounderList);
	}
	
	/**
	 * UC14
	 * @return
	 */
	
	public String getMostRunsAndWicketsCricketers() {
		List<AllRounder> allRounderList = getAllRoundersList();
		
		List<AllRounder> battingAllRounderList = allRounderList.stream()
				.filter(n -> n.getBowlingAverage() > 0 & n.getBattingAverage() > 0)
					  .sorted(Comparator.comparing(AllRounder::getRunsScored).reversed())
					  .collect(Collectors.toList());
		
		List<AllRounder> bowlingAllRounderList = allRounderList.stream()
				.filter(n -> n.getBowlingAverage() > 0 & n.getBattingAverage() > 0)
				  .sorted(Comparator.comparing(AllRounder::getWicketsTaken).reversed())
				  .collect(Collectors.toList());

		List<AllRounder> sortedAllRounderList = new ArrayList<>();
		sortedAllRounderList.add(battingAllRounderList.get(0)); 
		sortedAllRounderList.add(bowlingAllRounderList.get(0));
		return toJson(sortedAllRounderList);
	}
	
	
	/**
	 * UC15
	 * @return
	 */
	
	public String getMaximumHundredsCricketers() {
		List<Batsman> sortedBatsmanList = batsmanList.stream()
				.filter(n -> n.getCenturies() > 0)
				.sorted(Comparator.comparing(Batsman::getCenturies).reversed()
				.thenComparing(Batsman::getAverage).reversed())
				.collect(Collectors.toList());
		return toJson(sortedBatsmanList);
	}
	
	
	/**
	 * UC16
	 * @return
	 */
	
	public String getBestBattingAverageBelowFiftyCricketers() {
		List<Batsman> sortedBatsmanList = batsmanList.stream()
				.filter(n -> n.getCenturies() == 0 && n.getFifties() == 0)
				.sorted(Comparator.comparing(Batsman::getAverage).reversed())
				.collect(Collectors.toList());
		return toJson(sortedBatsmanList);
	}
	public <E> String toJson(List<E> list) {
		return new Gson().toJson(list);
	}

}
