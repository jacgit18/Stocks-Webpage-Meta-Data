package edu.citytech.cst.service;

import java.io.File;
import java.io.IOException;
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
		// TODO Auto-generated method stub
		return null;
	}

}
