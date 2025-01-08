package org.vuong.shopo.application.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRequest implements Serializable {

    private String grantType;
    private String clientId;
    private String clientSecret;
    private String code;
    private String username;
    private String password;

}
