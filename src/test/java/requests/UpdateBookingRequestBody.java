package requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookingRequestBody {

    private String firstname;
    private String lastname;
//    private Number totalprice;
//    private Boolean depositpaid;
//    private responces.Bookingdates bookingdates;
//    private String additionalneeds;

}
