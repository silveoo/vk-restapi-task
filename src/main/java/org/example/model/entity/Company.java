package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @JsonProperty("name")
    private String name;

    @JsonProperty("catchPhrase")
    private String catchPhrase;

    @JsonProperty("bs")
    private String bs;
}
