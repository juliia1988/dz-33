package responces;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookingdates {

    private Date checkin;
    private Date checkout;

}
