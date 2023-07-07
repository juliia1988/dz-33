package responces;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginBody {

    private String username;
    private String password;

}
