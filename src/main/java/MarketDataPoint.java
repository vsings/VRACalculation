import java.sql.Date;

public class MarketDataPoint {

    Integer instrumentId;
    Date date;
    Double closingPrice;

    public MarketDataPoint(Integer instrumentId, Date date, Double closingPrice) {
        this.instrumentId = instrumentId;
        this.date = date;
        this.closingPrice = closingPrice;
    }

    public Integer getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Integer instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
    }
}
