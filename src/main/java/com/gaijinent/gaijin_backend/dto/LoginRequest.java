package com.gaijinent.gaijin_backend.dto;

package com.gaijinent.gaijin_backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}