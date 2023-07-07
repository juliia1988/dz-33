package responces;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
public class Bookingdates {

    private Date checkin;
    private Date checkout;

}
