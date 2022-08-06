
package com.lloydsbank.atms.api.lloydsbankatmsapi.dto.branch;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class to form Response payload object of type BranchesResponse class and is returned when Lloyds Bank Branch Open Api service is called
 *  @author Ujwal Joshi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "StandardAvailability"
})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Availability {

    @JsonProperty("StandardAvailability")
    public StandardAvailability standardAvailability;

}
