package edu.axboot.controllers.pmsDto.reservation;

import edu.axboot.controllers.pmsDto.memo.RevMemoSaveRequestDto;
import edu.axboot.domain.hotel.reservation.RevRegister;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RevSaveRequestDto {
    private Long id;
    private String arrDt;
    private String depDt;
    private Integer nightCnt;
    private String roomTypCd;
    private Integer adultCnt;
    private Integer chldCnt;

    private String saleTypCd;
    private String srcCd;
    private String payCd;
    private String advnYn;
    private BigDecimal salePrc;
    private BigDecimal svcPrc;

    private Long guestId;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String langCd;
    private String brth;
    private String gender;

    private List<RevMemoSaveRequestDto> memos = new ArrayList<RevMemoSaveRequestDto>();


    public RevSaveRequestDto(Long id, String arrDt, String depDt, Integer nightCnt, String roomTypCd,
                             Integer adultCnt, Integer chldCnt, String saleTypCd, String srcCd, String payCd,
                             String advnYn, BigDecimal salePrc, BigDecimal svcPrc, Long guestId, String guestNm,
                             String guestNmEng, String guestTel, String email, String langCd, String brth,
                             String gender, List<RevMemoSaveRequestDto> memos) {
        this.id = id;
        this.arrDt = arrDt;
        this.depDt = depDt;
        this.nightCnt = nightCnt;
        this.roomTypCd = roomTypCd;
        this.adultCnt = adultCnt;
        this.chldCnt = chldCnt;
        this.saleTypCd = saleTypCd;
        this.srcCd = srcCd;
        this.payCd = payCd;
        this.advnYn = advnYn;
        this.salePrc = salePrc;
        this.svcPrc = svcPrc;
        this.guestId = guestId;
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.langCd = langCd;
        this.brth = brth;
        this.gender = gender;
        this.memos = memos;
    }

    public RevRegister toEntity() {
        return RevRegister.builder()
                .id(id)
                .arrDt(arrDt)
                .depDt(depDt)
                .nightCnt(nightCnt)
                .roomTypCd(roomTypCd)
                .adultCnt(adultCnt)
                .chldCnt(chldCnt)
                .saleTypCd(saleTypCd)
                .srcCd(srcCd)
                .payCd(payCd)
                .advnYn(advnYn)
                .salePrc(salePrc)
                .svcPrc(svcPrc)
                .guestId(guestId)
                .guestNm(guestNm)
                .guestNmEng(guestNmEng)
                .guestTel(guestTel)
                .email(email)
                .langCd(langCd)
                .brth(brth)
                .gender(gender)
                .build();
    }
}
