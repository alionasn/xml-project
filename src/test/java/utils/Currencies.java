package utils;

public enum Currencies {
    GBP("GBP"),
    SEK("SEK"),
    LEI("LEI");

    private String value;

    Currencies(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
