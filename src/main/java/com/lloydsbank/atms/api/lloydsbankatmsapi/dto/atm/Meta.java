
package com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class to form Response payload object of type AtmsResponse class and is returned when Lloyds Bank Atms Open Api service is called
 *  @author Ujwal Joshi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "LastUpdated",
    "TotalResults",
    "Agreement",
    "License",
    "TermsOfUse"
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meta {

    @JsonProperty("LastUpdated")
    public String lastUpdated;
    @JsonProperty("TotalResults")
    public Integer totalResults;
    @JsonProperty("Agreement")
    public String agreement;
    @JsonProperty("License")
    public String license;
    @JsonProperty("TermsOfUse")
    public String termsOfUse;

}
