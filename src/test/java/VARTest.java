import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class VARTest {
    MarketData marketData;
    Trade trade;
    Trade trade2;
    Portfolio portfolio;

    @BeforeEach
    void setUp() {
        marketData = new MarketData();
        marketData.generateRandomMarketDataForaYear(1);
        marketData.generateRandomMarketDataForaYear(2);
        trade = new Trade(1,100.00,"Security");
        portfolio = new Portfolio();
        trade2 = new Trade(2,200.00,"Security");
        portfolio.addTrade(trade);
        portfolio.addTrade(trade2);

    }

    @Test
    void TestDailyChangePercentage() {
        ArrayList<Double> result = VAR.dailyChangePercentage(trade,marketData);
        assertEquals(364,result.size());
    }

    @Test
    void TestFindPercentile() {
        ArrayList<Double> dailyChange = VAR.dailyChangePercentage(trade,marketData);
        double result = VAR.findPercentile(dailyChange,95);
        int position = (int) Math.ceil((dailyChange.size())*(100.0-95.0)/100.0);
        Collections.sort(dailyChange);
        assertEquals(dailyChange.get(position),result);
    }

    @Test
    void TestCalculateVARForTrade() {
        Double var1 = VAR.calculateVARForTrade(95,trade,marketData);
        Double var2 = VAR.calculateVARForTrade(95,trade2,marketData);
        System.out.println(var1);
        System.out.println(var2);
    }

    @Test
    void TestCalculateVARForPortfolio() {
        Double var=VAR.calculateVARForPortfolio(95,portfolio,marketData);

        System.out.println(var);
    }
}