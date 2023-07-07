package responces;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBookingResponceData {
    private String firstname;
    private String lastname;
    private Number totalprice;
    private Boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;
}
