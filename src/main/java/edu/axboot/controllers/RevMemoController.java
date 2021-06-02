package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.chequer.axboot.core.utils.MessageUtils;
import edu.axboot.controllers.pmsDto.memo.RevMemoSaveRequestDto;
import edu.axboot.controllers.pmsDto.reservation.RevSaveRequestDto;
import edu.axboot.domain.hotel.reservation.RevRegisterService;
import edu.axboot.domain.hotel.room.RoomInfo;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.hotel.reservation.memo.RevMemo;
import edu.axboot.domain.hotel.reservation.memo.RevMemoService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/revmemo")
public class RevMemoController extends BaseController {

    @Inject
    private RevRegisterService revRegisterService;
    private RevMemoService revMemoService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<RevMemo> requestParams) {
        List<RevMemo> list = revMemoService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }
/*

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<RevMemo> request) {
        revRegisterService.save(request);
        return ok();
    }
*/

}