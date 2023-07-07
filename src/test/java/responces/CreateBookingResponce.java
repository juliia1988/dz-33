package responces;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingResponce {
    private Number bookingid;
    private BookingData booking;
    private String additionalneeds;

}
