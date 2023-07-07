package responces;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginBody {

//    "username" : "admin",
//     "password" : "password123"

    private String username;
    private String password;

}
