import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class MarketDataTest {
    MarketData marketData;
    Date date;
    MarketDataPoint marketDataPoint;
    MarketDataPoint marketDataPoint1;

    @BeforeEach
    void setUp() {
        marketData= new MarketData();
        date = new Date(System.currentTimeMillis());
        marketDataPoint= new MarketDataPoint(1,date,200.00);
        marketDataPoint1 = new MarketDataPoint(1,date,300.00);
    }

    @Test
    void TestAddMarketDataPoint() {
        marketData.addMarketDataPoint(marketDataPoint);
        assertEquals(1,marketData.getMarketDataMap().get(1).size());
        marketData.addMarketDataPoint(marketDataPoint1);
        assertEquals(2,marketData.getMarketDataMap().get(1).size());

    }
}