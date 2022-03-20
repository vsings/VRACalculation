import java.util.ArrayList;

public class Portfolio {
    ArrayList<Trade>  trades;

    public Portfolio() {
        trades= new ArrayList<>();
    }

    public Portfolio(ArrayList<Trade> trades) {
        this.trades = trades;
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

}
