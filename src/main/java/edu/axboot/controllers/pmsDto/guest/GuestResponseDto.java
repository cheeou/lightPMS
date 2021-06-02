package edu.axboot.controllers.pmsDto.guest;

import edu.axboot.domain.hotel.guest.Guest;
import edu.axboot.domain.hotel.reservation.RevRegister;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GuestResponseDto {
    private Long id;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;
    private String langCd;
    private String rmk;

    private List<RevRegister> revRegisterList = new ArrayList<RevRegister>();

    public GuestResponseDto(Guest entity) {
        this.id = entity.getId();
        this.guestNm = entity.getGuestNm();
        this.guestNmEng = entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();
        this.langCd = entity.getLangCd();
        this.rmk = entity.getRmk();

        this.revRegisterList.addAll(entity.getRevRegisterList());   // 배열 선언 후 add 해야함
    }
}
