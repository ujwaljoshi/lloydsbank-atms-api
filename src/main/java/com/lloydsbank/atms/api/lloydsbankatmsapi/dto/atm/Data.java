
package com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * POJO class to form Response payload object of type AtmsResponse class and is returned when Lloyds Bank Atms Open Api service is called
 *  @author Ujwal Joshi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Brand"
})
@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty("Brand")
    public List<Brand> brand;

}
