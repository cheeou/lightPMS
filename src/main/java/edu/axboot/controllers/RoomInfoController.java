package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.pmsDto.room.RoomInfoListResponseDto;
import edu.axboot.domain.hotel.room.RoomInfo;
import edu.axboot.domain.hotel.room.RoomInfoService;
import edu.axboot.utils.MiscUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/hotel/roomInfo")
public class RoomInfoController extends BaseController {

    @Inject
    private RoomInfoService roomInfoService;

    @RequestMapping(method = {RequestMethod.GET}, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<RoomInfo> requestParams) {
        List<RoomInfo> list = roomInfoService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }


    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<RoomInfo> request) {
        roomInfoService.saveUsingQueryDsl(request);
        return ok();
    }

    @RequestMapping(value = "select" , method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse list2(RequestParams<RoomInfoListResponseDto> requestParams) {
        List<RoomInfoListResponseDto> list = roomInfoService.getListQueryDsl(requestParams);
        Page<RoomInfoListResponseDto> page = MiscUtils.toPage(list, requestParams.getPageable());
        return Responses.PageResponse.of(page);
    }


}