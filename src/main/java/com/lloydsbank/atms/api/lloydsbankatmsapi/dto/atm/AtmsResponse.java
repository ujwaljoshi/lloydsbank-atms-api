
package com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The Response payload object of type AtmsResponse class is returned when Lloyds Bank Atms Open Api service is called
 *  @author Ujwal Joshi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "meta",
    "data"
})
@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtmsResponse {

    @JsonProperty("meta")
    public Meta meta;
    @JsonProperty("data")
    public List<Data> data;

}
