package core.payload.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayloadEntityReads<T> {
    @JsonProperty("@odata.context")
    private String odata;
    @JsonProperty("value")
    private T value;

    public PayloadEntityReads(String odata, T value) {
        this.odata = odata;
        this.value = value;
    }

    public PayloadEntityReads() {}

    public String getOdata() {
        return odata;
    }

    public void setOdata(String odata) {
        this.odata = odata;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
