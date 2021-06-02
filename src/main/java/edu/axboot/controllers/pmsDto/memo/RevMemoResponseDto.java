package edu.axboot.controllers.pmsDto.memo;

import edu.axboot.domain.hotel.reservation.memo.RevMemo;
import lombok.Builder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RevMemoResponseDto {
    private Long id;
    private String memoCn;
    private String memoDate;

    public RevMemoResponseDto(RevMemo entity) {
        DateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        this.id = id;
        this.memoCn = memoCn;
        this.memoDate = format.format(entity.getMemoDtti());
    }
}
