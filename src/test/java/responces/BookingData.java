package responces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingData {
    private String firstname;
    private String lastname;
    private Number totalprice;
    private Boolean depositpaid;
    private Bookingdates bookingdates;

}
