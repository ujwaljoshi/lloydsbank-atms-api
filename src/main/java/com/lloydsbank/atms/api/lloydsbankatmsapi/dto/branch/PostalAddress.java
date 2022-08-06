
package com.lloydsbank.atms.api.lloydsbankatmsapi.dto.branch;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

/**
 * POJO class to form Response payload object of type BranchesResponse class and is returned when Lloyds Bank Branch Open Api service is called
 *  @author Ujwal Joshi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AddressLine",
    "TownName",
    "CountrySubDivision",
    "Country",
    "PostCode",
    "GeoLocation"
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddress {

    @JsonProperty("AddressLine")
    public List<String> addressLine;
    @JsonProperty("TownName")
    public String townName;
    @JsonProperty("CountrySubDivision")
    public List<String> countrySubDivision;
    @JsonProperty("Country")
    public String country;
    @JsonProperty("PostCode")
    public String postCode;
    @JsonProperty("GeoLocation")
    public GeoLocation geoLocation;

}
