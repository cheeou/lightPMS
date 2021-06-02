package edu.axboot.domain.hotel.room;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.pmsDto.room.RoomInfoListResponseDto;
import edu.axboot.domain.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomInfoService extends BaseService<RoomInfo, Long> {

    private RoomInfoRepository roomInfoRepository;

    @Autowired
    private RoomInfoService roomInfoService;

    @Inject
    public RoomInfoService(RoomInfoRepository roomInfoRepository) {
        super(roomInfoRepository);
        this.roomInfoRepository = roomInfoRepository;
    }

    public List<RoomInfo> gets(RequestParams<RoomInfo> requestParams) {
        return findAll();
    }

    @Transactional
    public void saveUsingQueryDsl(List<RoomInfo> requests) {
        for (RoomInfo roomInfo: requests) {
            if (roomInfo.isCreated()) {
                save(roomInfo);
            } else if (roomInfo.isModified()) {
                update(qRoomInfo)
                        .set(qRoomInfo.roomNum, roomInfo.getRoomNum())
                        .set(qRoomInfo.roomTypCd, roomInfo.getRoomTypCd())
                        .set(qRoomInfo.dndYn, roomInfo.getEbYn())
                        .set(qRoomInfo.ebYn, roomInfo.getEbYn())
                        .set(qRoomInfo.clnSttusCd, roomInfo.getClnSttusCd())
                        .set(qRoomInfo.svcSttusCd, roomInfo.getSvcSttusCd())
                        .where(qRoomInfo.id.eq(roomInfo.getId()))
                        .execute();
            } else if (roomInfo.isDeleted()) {
                delete(qRoomInfo)
                    .where(qRoomInfo.id.eq(roomInfo.getId()))
                    .execute();
            }
        }
    }

    public List<RoomInfoListResponseDto> getListQueryDsl(RequestParams<RoomInfoListResponseDto> requestParams) {
        String roomTypCd = requestParams.getString("roomTypCd", "");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(roomTypCd)) {
            builder.and(qRoomInfo.roomTypCd.eq(roomTypCd));
        }
        List<RoomInfo> entitis = select()
                .from(qRoomInfo)
                .where(builder)
                .orderBy(qRoomInfo.roomNum.asc())
                .fetch();
        return entitis.stream()
                .map(RoomInfoListResponseDto::new)
                .collect(Collectors.toList());
    }




}