package com.capgemini.ipl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class IPL_LeagueAnalyserTest {
	
	private static final String BATSMAN_DATA_CSV_PATH = "./src/test/resources/BatsmanData.csv";
	private static final String BOWLER_DATA_CSV_PATH = "./src/test/resources/BowlerData.csv";
	IPL_LeagueAnalyser ipl_LeagueAnalyser = null;
	
	@Before
	public void setUp() {
		ipl_LeagueAnalyser = new IPL_LeagueAnalyser();
		try {
			ipl_LeagueAnalyser.loadBatsmanData(BATSMAN_DATA_CSV_PATH);
			ipl_LeagueAnalyser.loadBowlerData(BOWLER_DATA_CSV_PATH);
		} catch (IPLAnalyserException e) {
		}
	}
	
	/**
	 * UC1 UC5
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_TopBattingAverages_WithBestStrikeRates() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getBestBattingAveragesCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		assertEquals("MS Dhoni", sortedBatsmanArray[0].getName());
	}
	
	/**
	 * UC2 UC4
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_TopStrikingRates_WithMaximumBoundries() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getBestStrikeRateCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		assertEquals("Andre Russell", sortedBatsmanArray[0].getName());
	}
	
	/**
	 * UC3
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumBoundries() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getMaximumBoundriesCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		assertEquals("Andre Russell", sortedBatsmanArray[0].getName());
	}
	
	/**
	 * UC6
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumRuns_WithBestAverages() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getMaximumRunsCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		assertEquals("David Warner", sortedBatsmanArray[0].getName());
	}
	
	/**
	 * UC7
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumBowlingAverages() {
		String sortedBowlerData = ipl_LeagueAnalyser.getMaximumBowlingAverageCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		System.out.println(sortedBowlerArray[0].getName() + sortedBowlerArray[0].getAverage());
		assertEquals("Anukul Roy", sortedBowlerArray[0].getName());
	}
}
