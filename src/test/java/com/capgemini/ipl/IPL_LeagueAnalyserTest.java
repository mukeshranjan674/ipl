package com.capgemini.ipl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class IPL_LeagueAnalyserTest {
	
	private static final String BATSMAN_DATA_PATH = "./src/test/resources/BatsmanData.csv";
	IPL_LeagueAnalyser ipl_LeagueAnalyser = null;
	
	@Before
	public void setUp() {
		ipl_LeagueAnalyser = new IPL_LeagueAnalyser();
		try {
			ipl_LeagueAnalyser.loadBatsmanData(BATSMAN_DATA_PATH);
		} catch (IPLAnalyserException e) {
		}
	}
	
	/**
	 * UC1 UC5
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_TopBattingAverages() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getBestBattingAveragesCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getAverage());
		assertEquals("MS Dhoni", sortedBatsmanArray[0].getName());
	}
	
	/**
	 * UC2 UC4
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_TopStrikingRates() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getBestStrikeRateCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getName()+ sortedBatsmanArray[0].getStrikeRate());
		assertEquals("Andre Russell", sortedBatsmanArray[0].getName());
	}
	
	/**
	 * UC3
	 */
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumBoundries() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getMaximumBoundriesCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getName() + sortedBatsmanArray[0].getBoundries());
		assertEquals("Andre Russell", sortedBatsmanArray[0].getName());
	}
}
