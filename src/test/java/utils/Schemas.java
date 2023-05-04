package utils;

public enum Schemas {
    PACS008001("pacs.008.001.08");

    private String schema;

    Schemas(String schema){
        this.schema=schema;
            }

    public String getSchema() {
        return schema;
    }
}
