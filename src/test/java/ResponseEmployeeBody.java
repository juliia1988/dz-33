import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ResponseEmployeeBody {

    private String status;

    private ResponceDataId data;

    private String message;

}
