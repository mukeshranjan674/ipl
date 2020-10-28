package com.capgemini.ipl;

import java.util.Comparator;
import java.util.List;
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
	 * UC7
	 * 
	 * @return
	 */
	public String getMaximumBowlingAverageCricketers() {
		List<Bowler> sortedBowlerList = bowlerList.stream()
				.filter(n -> n.getAverage()>0)
				.sorted(Comparator.comparing(Bowler::getAverage))
				.collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}
	
	/**
	 * UC8
	 * 
	 * @return
	 */
	public String getMaximumBowlingStrikeRatesCricketers() {
		List<Bowler> sortedBowlerList = bowlerList.stream()
				.filter(n -> n.getStrikeRate()>0)
				.sorted(Comparator.comparing(Bowler::getStrikeRate))
				.collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}

	public <E> String toJson(List<E> list) {
		return new Gson().toJson(list);
	}

}
