public class Trade {
    Integer instrumentId;
    Double amount;
    String  type;

    public Trade(Integer id,Double amount, String type) {
        this.instrumentId = id;
        this.amount = amount;
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setQuantity(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Integer instrumentId) {
        this.instrumentId = instrumentId;
    }
}
