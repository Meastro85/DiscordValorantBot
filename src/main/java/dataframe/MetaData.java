package dataframe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MetaData(String map) {

    public MetaData(@JsonProperty("map") String map){
        this.map = map;
    }
}
