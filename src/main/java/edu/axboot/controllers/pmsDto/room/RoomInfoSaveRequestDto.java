package edu.axboot.controllers.pmsDto.room;

import edu.axboot.domain.hotel.room.RoomInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomInfoSaveRequestDto {
    private Long id;
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
    public RoomInfoSaveRequestDto(RoomInfo entity){
        this.id = entity.getId();
        this.roomNum = entity.getRoomNum();
        this.roomTypCd = entity.getRoomTypCd();
        this.dndYn = entity.getDndYn();
        this.ebYn = entity.getEbYn();
        this.roomSttusCd = entity.getRoomSttusCd();
        this.clnSttusCd = entity.getClnSttusCd();
        this.svcSttusCd = entity.getSvcSttusCd();
        this.__created__ = false;
        this.__modified__ = false;
        this.__deleted__ = false;
    }

    public RoomInfo toEntity() {
        return RoomInfo.builder()
                .id(id)
                .roomNum(roomNum)
                .roomTypCd(roomTypCd)
                .dndYn(dndYn)
                .ebYn(ebYn)
                .roomSttusCd(roomSttusCd)
                .clnSttusCd(clnSttusCd)
                .svcSttusCd(svcSttusCd)
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .build();
    }
}
