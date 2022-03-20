import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class MarketData {
    HashMap<Integer,ArrayList<MarketDataPoint>> marketDataMap;

    public MarketData() {
        marketDataMap = new HashMap<>();
    }

    public HashMap<Integer,ArrayList<MarketDataPoint>> getMarketDataMap() {
        return marketDataMap;
    }

    public void addMarketDataPoint(MarketDataPoint marketDataPoint) {
        ArrayList<MarketDataPoint> marketDataPoints;
        if(marketDataMap.containsKey(marketDataPoint.getInstrumentId())){
            marketDataPoints = marketDataMap.get(marketDataPoint.getInstrumentId());
        }
        else {
            marketDataPoints = new ArrayList<>();
        }
        marketDataPoints.add(marketDataPoint);
        this.marketDataMap.put(marketDataPoint.getInstrumentId(),marketDataPoints);
    }


    public void generateRandomMarketDataForaYear(Integer instrumentID){
        Date date = Date.valueOf("2015-1-1");
        for (int i = 0; i < 365; i++) {
            date.setTime((date.getTime()) +86400000);//Adding one day
            Double closingPrice = (Math.random()*(150-100)+100);//Creating random closingValues between 100 and 150.
            MarketDataPoint marketDataPoint = new MarketDataPoint(instrumentID,date,closingPrice);
            addMarketDataPoint(marketDataPoint);
        }
    }
}
