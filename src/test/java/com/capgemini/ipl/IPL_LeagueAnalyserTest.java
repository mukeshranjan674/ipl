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
	 * UC7 UC11
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumBowlingAverages_WithBestStrikeRate() {
		String sortedBowlerData = ipl_LeagueAnalyser.getMaximumBowlingAverageCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		assertEquals("Anukul Roy", sortedBowlerArray[0].getName());
	}

	/**
	 * UC8 UC10
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumBowlingStrikeRatesAndHauls() {
		String sortedBowlerData = ipl_LeagueAnalyser.getMaximumBowlingStrikeRatesCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		assertEquals("Alzarri Joseph", sortedBowlerArray[0].getName());
	}

	/**
	 * UC9
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_BestEconomy() {
		String sortedBowlerData = ipl_LeagueAnalyser.getBestEconomyCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		assertEquals("Shivam Dube", sortedBowlerArray[0].getName());
	}

	/**
	 * UC12
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumWickets_WithBestBowlingAverages() {
		String sortedBowlerData = ipl_LeagueAnalyser.getMaximumWicketsCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		assertEquals("Imran Tahir", sortedBowlerArray[0].getName());
	}

	/**
	 * UC12
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_BattingAndBowlingAverages() {
		String sortedBowlerData = ipl_LeagueAnalyser.getBattingAndBowlingAveragesCricketers();
		AllRounder[] sortedAllRounderArray = new Gson().fromJson(sortedBowlerData, AllRounder[].class);
		System.out.println(sortedAllRounderArray[1].getName() + sortedAllRounderArray[1].getBattingAverage()
				+ sortedAllRounderArray[1].getBowlingAverage());
		assertEquals("Andre Russell", sortedAllRounderArray[0].getName());
		assertEquals("Kagiso Rabada", sortedAllRounderArray[1].getName());
	}
}
