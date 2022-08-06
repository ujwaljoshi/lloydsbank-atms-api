
package com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * POJO class to form Response payload object of type AtmsResponse class and is returned when Lloyds Bank Atms Open Api service is called
 *  @author Ujwal Joshi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Identification",
    "SupportedCurrencies",
    "Location"
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Atm {

    @JsonProperty("Identification")
    public String identification;
    @JsonProperty("SupportedCurrencies")
    public List<String> supportedCurrencies;
    @JsonProperty("Location")
    public Location location;

}
