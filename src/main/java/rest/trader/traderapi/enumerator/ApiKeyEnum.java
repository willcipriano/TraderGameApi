package rest.trader.traderapi.enumerator;

public enum ApiKeyEnum {
    ALPHA("Alpha Vantage", "alpha"), UNDEFINED("Undefined", "undefined");

    private String name;
    private String shorter;

    ApiKeyEnum(String humanReadableName, String shortLowercaseName) {
        name = humanReadableName;
        shorter = shortLowercaseName;
    }


    public String toShortAndLower() {
        return shorter;
    }

    public String toString() {
        return name;
    }
}
