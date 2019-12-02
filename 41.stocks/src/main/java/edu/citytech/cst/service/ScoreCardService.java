package edu.citytech.cst.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ScoreCardService {
	
	private Document doc = null;

	public Document getDocument(String filename) {
		
		// crtl + shift + o  to get rid of elements not used like imports
		File input = new File("/home/jac/Stocks/MID Dividend Scorecard.html");
		try {
			 doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			
			return doc;
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return null;
	
	}
	
	
	
	public String getStockName() {
		Elements metas = this.doc.select("div .symbol_title meta"); 
		
		String name = metas.select("meta[itemprop=name]").first().attr("content");
		
		return name;
	}



	public String getSymbol() {

        Elements metas = this.doc.select("div .symbol_title meta"); 
		
		String symbol = metas.select("meta[itemprop=tickerSymbol]").first().attr("content");
		
		
		return symbol;
	}
	
	
	
	public float getPrice() {

		
		String Sprice = this.doc.select("div#symbol_last_trade").first().text().replace("$", ""); 
		// Replace $ helps deal with $ which messes with parse
		
		float price = Float.parseFloat(Sprice);
		
		
		return price;
	}



	public Map<EDividendSummary, Float> getDividendSummary() {

		Map<EDividendSummary, Float> map = new HashMap<>();
		
		for (EDividendSummary e : EDividendSummary.values()) {
			map.put(e, 0f);
		}
		
		// populate the hashmap with the correct value here 
		
		// <td id="dividendsYield" data-value="2.82%">2.82%</td>
		String cssQuery1 = "#dividendsYield[data-value]";
		
		String sYield = this.doc.select(cssQuery1).first().text().replace("%", ""); 
		
		float yield = Float.parseFloat(sYield) / 100f;
		map.put(EDividendSummary.DIVIDEND_YEILD, yield);
		
//		<td id="dividendsRate" data-value="$3.84">$3.84</td> Annual Payout
		String cssQuery2 = "#dividendsRate[data-value]";
		
		String AnnualPayOut = this.doc.select(cssQuery2).first().text().replace("$", ""); 
		
		float Annualyield = Float.parseFloat(AnnualPayOut) ;
		map.put(EDividendSummary.ANNUAL_PAYOUT, Annualyield);
 		
//		<td id="dividendsPayoutRatio" data-value="59.83%">59.83%</td>
		String cssQuery3 = "#dividendsPayoutRatio[data-value]";
		
		String PayoutRatio = this.doc.select(cssQuery3).first().text().replace("%", ""); 
		
		float divPayoutRatio = Float.parseFloat(PayoutRatio) ;
		map.put(EDividendSummary.PAYOUT_RATIO, divPayoutRatio);
		
//		<td id="fiveYearGrowth" data-value="5.83%">5.83%</td>
		String cssQuery4 = "#fiveYearGrowth[data-value]";
		
		String yearGrowth = this.doc.select(cssQuery4).first().text().replace("%", ""); 
		
		float FiveyearGrowth = Float.parseFloat(yearGrowth) ;
		map.put(EDividendSummary.FIVE_YEAR_GROWTH_RATE, FiveyearGrowth);
		
//		<td id="dividendsGrowthYears" data-value="9 Years">9 Years</td>
	    String cssQuery5 = "#dividendsGrowthYears[data-value]";
		
		String divGrowth = this.doc.select(cssQuery5).first().text().replace("Years", ""); 

		float growconvert = Float.parseFloat(divGrowth);
		map.put(EDividendSummary.DIVIDEND_GROWTH, growconvert);
		// how to hide numbers after decicimal and the decimal in a float datatype

		
		return map;
	}

}
