package edu.axboot.domain.hotel.room;

import edu.axboot.AXBootApplication;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/*@Log
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)

public class RoomInfoTest{
    @Value("${axboot.upload.repository}")
    public String uploadRepository;
@Autowired private RoomInfoService roomInfoService;
    @SneakyThrows   //명시적인 예외처리 생략
    @Test

    public void testSaveList() {
        RoomInfo roomInfo = new RoomInfo();

        roomInfo.setId(6L);
        roomInfo.setRoomNum("404");
        roomInfo.setRoomTypCd("SB");
        List<RoomInfo> list = new ArrayList<RoomInfo>();
        list.add(roomInfo);

      // List<RoomInfo> list = roomInfoService.save(roomInfo);
        list.add(roomInfo);

        roomInfoService.save(list);

    }

}*/
