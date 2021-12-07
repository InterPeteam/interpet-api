package com.interteam.interpet.api.controller.auth;

import com.interteam.interpet.api.model.User;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class TokenTransfer {
    private String token;
    private User user;
}
