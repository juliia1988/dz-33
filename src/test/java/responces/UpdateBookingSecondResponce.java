package responces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBookingSecondResponce {
    private String firstname;
    private String lastname;
    private Number totalprice;
    private Boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

}
