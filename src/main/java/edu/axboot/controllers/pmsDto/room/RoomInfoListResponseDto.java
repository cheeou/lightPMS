package edu.axboot.controllers.pmsDto.room;

import edu.axboot.domain.hotel.room.RoomInfo;
import lombok.Getter;

@Getter
public class RoomInfoListResponseDto {
    private Long id;
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;

    public RoomInfoListResponseDto(RoomInfo entity){
        this.id = entity.getId();
        this.roomNum = entity.getRoomNum();
        this.roomTypCd = entity.getRoomTypCd();
        this.dndYn = entity.getDndYn();
        this.ebYn = entity.getEbYn();
        this.roomSttusCd = entity.getRoomSttusCd();
        this.clnSttusCd = entity.getClnSttusCd();
        this.svcSttusCd = entity.getSvcSttusCd();
    }
}
