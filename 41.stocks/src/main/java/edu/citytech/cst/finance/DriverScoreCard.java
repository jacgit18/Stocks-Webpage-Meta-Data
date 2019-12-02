package edu.citytech.cst.finance;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DriverScoreCard {

	public static void main(String[] args) {
		File input = new File("/home/jac/Stocks/MID Dividend Scorecard.html");
		try {
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			
			Elements metas = doc.select("div .symbol_title meta"); // selector for css
			metas.forEach(System.out::println); // printing out meta tags
			
			String name = metas.select("meta[itemprop=name]").first().attr("content");
			String symbol = metas.select("meta[itemprop=tickerSymbol]").first().attr("content");

			System.out.println("--------------------------------------");
			System.out.println("Symbol: " + symbol);
			System.out.println(name);

			String mymoney = doc.select("div#symbol_last_trade").first().text(); // selector for css
			System.out.println(mymoney);
			
			
			String exchange = metas.select("meta[itemprop=exchange]").first().attr("content");
			System.out.println(exchange);
			
			String Price = metas.select("meta[itemprop=exchange]").first().attr("content");
			System.out.println(Price);
			
			
			
			
			
			//<td id="dividendsPayoutRatio" data-value="59.83%">59.83%</td>
			String ePayoutRatio = doc.select("td#dividendsPayoutRatio").first().text(); //# for tag id
			System.out.println(ePayoutRatio);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		

	}

}
