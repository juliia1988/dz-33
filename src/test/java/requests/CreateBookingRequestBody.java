package requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import responces.Bookingdates;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingRequestBody {

    private String firstname;
    private String lastname;
    private Number totalprice;
    private Boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

}
