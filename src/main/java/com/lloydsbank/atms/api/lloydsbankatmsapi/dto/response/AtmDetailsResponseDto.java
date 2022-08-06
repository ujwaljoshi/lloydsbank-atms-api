package com.lloydsbank.atms.api.lloydsbankatmsapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm.Atm;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * The Response payload object is of type AtmDetailsResponseDto class is
 * returned when newly exposed get endpoint is called
 *  @author Ujwal Joshi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Identification",
        "ATM"
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class AtmDetailsResponseDto {

    @JsonProperty(value = "Identification", required = true)
    public String identification;
    @JsonProperty(value = "ATM", required = true)
    public List<Atm> atm;

}
