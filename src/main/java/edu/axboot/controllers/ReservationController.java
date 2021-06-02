package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.chequer.axboot.core.utils.MessageUtils;
import edu.axboot.controllers.pmsDto.reservation.RevListResponseDto;
import edu.axboot.controllers.pmsDto.reservation.RevSaveRequestDto;
import edu.axboot.domain.hotel.reservation.RevRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.hotel.reservation.RevRegisterService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/hotel/revRegister")
public class ReservationController extends BaseController {

    private final RevRegisterService revRegisterService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(@RequestParam(value = "filter", required = false) String filter,
                                       @RequestParam(value = "rsvNum", required = false) String rsvNum,
                                       @RequestParam(value = "roomTypCd", required = false) String roomTypCd,
                                       @RequestParam(value = "rsvDtStart", required = false) String rsvSttDate,
                                       @RequestParam(value = "rsvDtEnd", required = false) String rsvEndDate,
                                       @RequestParam(value = "arrDtStart", required = false) String arrSttDate,
                                       @RequestParam(value = "arrDtEnd", required = false) String arrEndDate,
                                       @RequestParam(value = "depDtStart", required = false) String depSttDate,
                                       @RequestParam(value = "depDtEnd", required = false) String depEndDate,
                                       @RequestParam(value = "sttusCd", required = false) List<String> sttusCds) {
        List<RevListResponseDto> list = revRegisterService.findBy(filter, rsvNum, roomTypCd, rsvSttDate, rsvEndDate, arrSttDate, arrEndDate, depSttDate, depEndDate, sttusCds);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public Responses.MapResponse save(@RequestBody RevSaveRequestDto requestDto, HttpServletRequest request) {
        Long id = revRegisterService.save(requestDto);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("chkId", id);
        map.put("message", MessageUtils.getMessage(request, "ax.script.onsave"));
        return Responses.MapResponse.of(map);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public RevListResponseDto findById(@PathVariable Long id) {
        return revRegisterService.findById(id);
    }

}