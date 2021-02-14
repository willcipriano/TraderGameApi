package rest.trader.traderapi.enumerator;

public enum ApiKeyRequestorEnum {
    STOCK_DATA_SYSTEM("Stock Data System"),
    TEST("Test");

    public String label;

    ApiKeyRequestorEnum(String s) {
        label = s;
    }

    public String toString() {
        return label;
    }
}
