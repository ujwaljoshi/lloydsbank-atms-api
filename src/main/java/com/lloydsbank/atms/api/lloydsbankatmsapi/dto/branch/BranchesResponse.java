
package com.lloydsbank.atms.api.lloydsbankatmsapi.dto.branch;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

/**
 * The Response payload object of type BranchesResponse class is returned when Lloyds Bank Branch Open Api service is called
 *  @author Ujwal Joshi
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "meta",
    "data"
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchesResponse {

    @JsonProperty("meta")
    public Meta meta;
    @JsonProperty("data")
    public List<Data> data;

}
