package edu.axboot.controllers.pmsDto.guest;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestUpdateRequestDto {
    private Long id;
    private String guestTel;
    private String email;
    private String rmk;

    @Builder
    public GuestUpdateRequestDto(Long id, String guestTel, String email, String rmk) {
        this.id = id;
        this.guestTel = guestTel;
        this.email = email;
        this.rmk = rmk;
    }
}
