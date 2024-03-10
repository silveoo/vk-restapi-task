package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo {

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("lng")
    private String lng;
}
