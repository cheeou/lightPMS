package edu.axboot.domain.hotel.reservation;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.pmsDto.memo.RevMemoSaveRequestDto;
import edu.axboot.controllers.pmsDto.reservation.RevListResponseDto;
import edu.axboot.controllers.pmsDto.reservation.RevSaveRequestDto;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.hotel.guest.Guest;
import edu.axboot.domain.hotel.guest.GuestRepository;
import edu.axboot.domain.hotel.reservation.memo.RevMemo;
import edu.axboot.domain.hotel.reservation.memo.RevMemoRepository;
import edu.axboot.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RevRegisterService extends BaseService<RevRegister, Long> {

    private final RevRegisterRepository revRegisterRepository;
    private final GuestRepository guestRepository;
    private final RevMemoRepository revMemoRepository;

    @Transactional
    public long save(RevSaveRequestDto saveDto) { //예약정보 저장
        long id = 0;

        //투숙객정보 저장
        Guest guest = Guest.builder()
                .id(saveDto.getGuestId())
                .guestNm(saveDto.getGuestNm())
                .guestNmEng(saveDto.getGuestNmEng())
                .guestTel(saveDto.getGuestTel())
                .email(saveDto.getEmail())
                .brth(saveDto.getBrth())
                .gender(saveDto.getGender())
                .langCd(saveDto.getLangCd())
                .build();

        Long guestId = guestRepository.save(guest).getId();

        RevRegister rev = null;
        if (saveDto.getId() == null || saveDto.getId() == 0) {
            rev = saveDto.toEntity();
            rev.투숙객번호갱신(guestId);
            id = 신규예약저장(rev);
        } else {
            rev = revRegisterRepository.findOne(saveDto.getId());
            rev.투숙객번호갱신(guestId);
            rev.예약수정(guestId, saveDto.getGuestNm(), saveDto.getGuestNmEng(), saveDto.getGuestTel(), saveDto.getEmail(), saveDto.getBrth(), saveDto.getGender(), saveDto.getLangCd(),
                    saveDto.getArrDt(), saveDto.getDepDt(), saveDto.getNightCnt(), saveDto.getRoomTypCd(), saveDto.getAdultCnt(), saveDto.getChldCnt(),
                    saveDto.getSaleTypCd(), saveDto.getSrcCd(), saveDto.getPayCd(), saveDto.getAdvnYn(), saveDto.getSalePrc(), saveDto.getSvcPrc());
            id = saveDto.getId();
        }

        // 투숙메모 처리
        this.saveToMemo(rev.getRsvNum(), saveDto.getMemos());

        return id;
    }

    private Long 신규예약저장(RevRegister rev) {
            String rsvDt = LocalDate.now().toString();
            RevRegister todayLastChk = select().select(
                    Projections.fields(RevRegister.class, qRevRegister.sno))
                    .from(qRevRegister)
                    .where(qRevRegister.rsvDt.eq(rsvDt))
                    .orderBy(qRevRegister.sno.desc())
                    .fetchFirst();

            int sno = 1;
            if (todayLastChk != null) {
                sno = todayLastChk.getSno() + 1;
            }

            rev.예약번호생성(rsvDt, sno);

            return revRegisterRepository.save(rev).getId();
    }

    public List<RevRegister> getRevList(RequestParams<RevRegister> requestParams) {
            return findAll();
        }

    private void saveToMemo(String rsvNum, List<RevMemoSaveRequestDto> memoDtos) {
        for (RevMemoSaveRequestDto memoDto: memoDtos) {
            if (memoDto.is__created__()) {
                RevMemo lastRevMemo = select().select(
                        Projections.fields(RevMemo.class, qRevMemo.sno))
                        .from(qRevMemo)
                        .where(qRevMemo.rsvNum.eq(rsvNum))
                        .orderBy(qRevMemo.sno.desc())
                        .fetchFirst();

                int snoMemo = 1;
                if (lastRevMemo != null) {
                    snoMemo = lastRevMemo.getSno() + 1;
                }

                RevMemo memo = RevMemo.builder()
                        .rsvNum(rsvNum)
                        .sno(snoMemo)
                        .memoCn(memoDto.getMemoCn())
                        .memoDtti(Timestamp.valueOf(LocalDateTime.now()))
                        .memoMan(SessionUtils.getCurrentLoginUserCd())
                        .delYn("N")
                        .build();
                revMemoRepository.save(memo);
            } else if (memoDto.is__modified__()) {
                RevMemo memo = revMemoRepository.findOne(memoDto.getId());
                memo.update(memoDto.getMemoCn());
            } else if (memoDto.is__deleted__()) {
                RevMemo memo = revMemoRepository.findOne(memoDto.getId());
                memo.delete();
            }
        }
    }

    @Transactional(readOnly = true)
    public List<RevListResponseDto> findBy(String filter, String rsvNum, String roomTypCd, String rsvSttDate,
                                           String rsvEndDate, String arrSttDate, String arrEndDate, String depSttDate,
                                           String depEndDate, List<String> sttusCds) {
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(filter)) {
            BooleanBuilder builder2 = new BooleanBuilder();
            builder2.or(qRevRegister.guestNm.contains(filter));
            builder2.or(qRevRegister.guestTel.contains(filter));
            builder2.or(qRevRegister.email.contains(filter));
            builder.and(builder2);
        }

        if (isNotEmpty(rsvNum)) {
            builder.and(qRevRegister.rsvNum.contains(rsvNum));
        }

        if (isNotEmpty(roomTypCd)) {
            builder.and(qRevRegister.roomTypCd.eq((roomTypCd)));
        }

        if (isNotEmpty(rsvSttDate)) {
            if (isNotEmpty(rsvEndDate)) {
                builder.and(qRevRegister.rsvDt.between(rsvSttDate, rsvEndDate));
            } else {
                builder.and(qRevRegister.rsvDt.goe(rsvSttDate));
            }
        }

        if (isNotEmpty(arrSttDate)) {
            if (isNotEmpty(arrEndDate)) {
                builder.and(qRevRegister.arrDt.between(arrSttDate, arrEndDate));
            } else {
                builder.and(qRevRegister.arrDt.goe(arrSttDate));
            }
        }

        if (isNotEmpty(depSttDate)) {
            if (isNotEmpty(depEndDate)) {
                builder.and(qRevRegister.depDt.between(depSttDate, depEndDate));
            } else {
                builder.and(qRevRegister.depDt.goe(depSttDate));
            }
        }

        if (sttusCds != null) {
            if (sttusCds.size() > 0) {
                BooleanBuilder builder2 = new BooleanBuilder();
                for (String sttusCd: sttusCds) {
                    builder2.or(qRevRegister.sttusCd.eq(sttusCd));
                }
                builder.and(builder2);
            }
        }

        List<RevRegister> entities = select().select(
                Projections.fields(RevRegister.class,
                        qRevRegister.id, qRevRegister.rsvNum, qRevRegister.rsvDt, qRevRegister.arrDt, qRevRegister.depDt, qRevRegister.nightCnt,
                        qRevRegister.roomTypCd, qRevRegister.roomNum, qRevRegister.saleTypCd, qRevRegister.srcCd, qRevRegister.sttusCd,
                        qRevRegister.guestNm
                ))
                .from(qRevRegister)
                .where(builder)
                .orderBy(qRevRegister.rsvNum.asc())
                .fetch();

        return entities.stream()
                .map(RevListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RevListResponseDto findById(Long id) {
        RevRegister chk = revRegisterRepository.findOne(id);

        if (chk == null) {
            throw new IllegalArgumentException("해당 예약 정보가 없습니다. id=" + id);
        }

        return new RevListResponseDto(chk);
    }



}