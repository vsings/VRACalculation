import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class VAR {


    public static double calculateVARForTrade(Integer confidenceLevel, Trade trade,MarketData marketdata) {
        ArrayList<Double> dailyChange = dailyChangePercentage(trade, marketdata);
        return findPercentile(dailyChange,confidenceLevel);
    }

    public static double calculateVARForPortfolio(Integer confidenceLevel, Portfolio portfolio,MarketData marketData) {
        HashMap<Integer,ArrayList<Double>> dailyChangeForTrades= dailyChangePercentageForTrades(portfolio,marketData);
        ArrayList<Double> dailyChangeForPortfolio = predictForPortfolio(portfolio,dailyChangeForTrades);
        double varInAmount = findPercentile(dailyChangeForPortfolio,confidenceLevel);
        Double totalAmountForPortfolio = 0.0;
        for (Trade trade:portfolio.getTrades()) {
            totalAmountForPortfolio+=trade.getAmount();
        }
        return varInAmount/totalAmountForPortfolio;
    }

    public static double findPercentile(ArrayList<Double> dailyChange, Integer confidenceLevel) {
        Collections.sort(dailyChange);
        int position = (int) Math.ceil((dailyChange.size())*(100.0-confidenceLevel)/100.0);
        return dailyChange.get(position);
    }

    public static ArrayList<Double> dailyChangePercentage(Trade trade,MarketData marketdata){
        ArrayList<Double> dailyChange = new ArrayList<>();
        ArrayList<MarketDataPoint> marketdataPoints = marketdata.getMarketDataMap().get(trade.getInstrumentId());
        for (int i=0; i<marketdataPoints.size()-1;i++){
            dailyChange.add(((marketdataPoints.get(i+1).getClosingPrice() - marketdataPoints.get(i).getClosingPrice())/
                    marketdataPoints.get(i).getClosingPrice())*100);
        }
        return dailyChange;
    }

    public static HashMap<Integer,ArrayList<Double>> dailyChangePercentageForTrades(Portfolio portfolio, MarketData marketData){
        HashMap<Integer,ArrayList<Double>> dailyChangeForTrades = new HashMap<>();
        for(Trade trade : portfolio.getTrades()){
            ArrayList<Double> dailyChange = dailyChangePercentage(trade, marketData);
            dailyChangeForTrades.put(trade.getInstrumentId(),dailyChange);
        }
        return dailyChangeForTrades;
    }

    public static ArrayList<Double> predictForPortfolio(Portfolio portfolio, HashMap<Integer,ArrayList<Double>> dailyChangeForTrades) {
        ArrayList<ArrayList<Double>> listOfListsOfAmountChange = new ArrayList<>();
        ArrayList<Double> dailyChangeForPortfolio = new ArrayList<>();
        for (Trade trade : portfolio.getTrades()) {
            ArrayList<Double> listOfAmountChange = dailyChangeForTrades.get(trade.getInstrumentId());
            for (int i = 0; i < listOfAmountChange.size(); i++) {
                Double amountChange = trade.getAmount() * listOfAmountChange.get(i);//Getting the amount lost/gained by the % change *100
                listOfAmountChange.set(i, amountChange);//Replacing this calculated amount in the list
            }
            listOfListsOfAmountChange.add(listOfAmountChange);
        }
        for (int j=0; j < listOfListsOfAmountChange.get(0).size(); j++) {
            Double sumForAllTrades = 0.0;
            for (int i = 0; i < listOfListsOfAmountChange.size(); i++) {
                sumForAllTrades += listOfListsOfAmountChange.get(i).get(j);//Summing over the potential Loss/Gain per day
            }
            dailyChangeForPortfolio.add(sumForAllTrades);
        }
        return dailyChangeForPortfolio;//Returning a list of potential Gain/Loss per day
    }


}
