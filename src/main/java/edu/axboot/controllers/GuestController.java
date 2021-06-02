package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import edu.axboot.controllers.pmsDto.guest.GuestResponseDto;
import edu.axboot.domain.hotel.guest.GuestService;
import edu.axboot.utils.MiscUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor //final,notnull 필드 값만 파라미터로 받는 생성자 만들어줌
@RestController
@RequestMapping(value = "/api/v1/hotel/guest")
public class GuestController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(GuestController.class);

    private final GuestService guestService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "guestNm", value = "투숙객명", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "guestTel", value = "연락처", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "이메일", dataType = "String", paramType = "query"),
    })
    public Responses.PageResponse list(RequestParams<GuestResponseDto> requestParams) {
        List<GuestResponseDto> list = guestService.getList(requestParams);
        Page<GuestResponseDto> page = MiscUtils.toPage(list, requestParams.getPageable());
        return Responses.PageResponse.of(page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public GuestResponseDto findById(@PathVariable Long id) {
        return guestService.findById(id);
    }
/*    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(@RequestParam(value = "guestNm", required = false) String guestNm,
                                       @RequestParam(value = "guestTel", required = false) String guestTel,
                                       @RequestParam(value = "email", required = false) String email) {
        List<GuestListResponseDto> list = guestService.findBy(guestNm, guestTel, email);
        return Responses.ListResponse.of(list);
    }



    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody GuestSaveRequestDto requestDto) {
        guestService.save(requestDto);
        return ok();
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse update(@RequestBody GuestSaveRequestDto requestDto) {
        guestService.update(requestDto);
        return ok();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Guest viewUsingQueryDsl(@PathVariable Long id) {
        Guest entity = guestService.getOne(id);
        return entity;
    }
*/
}