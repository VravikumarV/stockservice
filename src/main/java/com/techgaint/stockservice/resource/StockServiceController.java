package com.techgaint.stockservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/stockService/stock/")
public class StockServiceController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/getStock/{symbol}")
    private String getStock(@PathVariable("symbol") final String symbol) {
        String yahooResponse = "";
        try {
            System.out.println("  The Stock Quote Symbol is:   "+symbol);
            /*ResponseEntity<String> dbResponse = restTemplate.exchange("http://db-service:8181/dbservice/quotes/getQuote", HttpMethod.GET,
                    null, new ParameterizedTypeReference<String>() {
                    });
            System.out.println("The DB Servoice response is : "+dbResponse.getBody());*/
            Stock stock = YahooFinance.get(symbol);
            yahooResponse = " Stock is:  "+stock.toString()+" /n StockQuote :  "+stock.getQuote().toString();
            System.out.println(" The response from Yahoo service is ---->  Symbol  :   "+stock.getSymbol()+" Currency :  "+stock.getCurrency()+" Previous Close:  "+stock.getQuote().getPreviousClose());
        }catch (Exception e){
            System.out.println(e.toString());
            //yahooResponse = "Issue with Yahoo finanace API";
            yahooResponse = "Yahoo Issue:  "+e.getStackTrace();
        }
        return yahooResponse;
    }

}
