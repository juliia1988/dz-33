package responces;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBookingFirstResponce {
    private Number totalprice;

}
