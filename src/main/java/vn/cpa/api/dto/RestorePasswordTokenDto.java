package vn.cpa.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RestorePasswordTokenDto {
    private String token;
    private LocalDateTime expiryDate;
}
