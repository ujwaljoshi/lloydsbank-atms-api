
package com.lloydsbank.atms.api.lloydsbankatmsapi.dto.branch;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * POJO class to form Response payload object of type BranchesResponse class and is returned when Lloyds Bank Branch Open Api service is called
 *  @author Ujwal Joshi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Identification",
    "SequenceNumber",
    "Name",
    "Type",
    "CustomerSegment",
    "Availability",
    "ContactInfo",
    "PostalAddress"
})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

    @JsonProperty("Identification")
    public String identification;
    @JsonProperty("SequenceNumber")
    public String sequenceNumber;
    @JsonProperty("Name")
    public String name;
    @JsonProperty("Type")
    public String type;
    @JsonProperty("CustomerSegment")
    public List<String> customerSegment;
    @JsonProperty("Availability")
    public Availability availability;
    @JsonProperty("ContactInfo")
    public List<ContactInfo> contactInfo;
    @JsonProperty("PostalAddress")
    public PostalAddress postalAddress;

}
