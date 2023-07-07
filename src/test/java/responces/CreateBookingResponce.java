package responces;
import lombok.*;

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
