
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
    "Name",
    "OpeningHours"
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Day {

    @JsonProperty("Name")
    public String name;
    @JsonProperty("OpeningHours")
    public List<OpeningHour> openingHours;

}
