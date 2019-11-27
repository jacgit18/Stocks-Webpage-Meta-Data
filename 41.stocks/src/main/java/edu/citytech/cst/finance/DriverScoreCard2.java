package edu.citytech.cst.finance;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;

import static edu.citytech.cst.service.EDividendSummary.*;
import edu.citytech.cst.service.EDividendSummary;
import edu.citytech.cst.service.ScoreCardService;

public class DriverScoreCard2 {

	public static void main(String[] args) {
		//

		ScoreCardService sc = new ScoreCardService();
		Document doc = sc.getDocument("/home/jac/Stocks/MID Dividend Scorecard.html");

		String name = sc.getStockName();
		System.out.println("Name: " + name);
		String symbol = sc.getSymbol();
		System.out.println("Symbol: " + symbol);

		float price = sc.getPrice();
		System.out.println("Price: " + price);

		Map <EDividendSummary, Float> map = new HashMap<>();
		map.put(DIVIDEND_YEILD, .0727f);
		
		Map <EDividendSummary, Float> dividendSummary = sc.getDividendSummary();


	}

}
