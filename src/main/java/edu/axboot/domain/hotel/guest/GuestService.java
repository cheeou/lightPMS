package edu.axboot.domain.hotel.guest;

import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.pmsDto.guest.GuestListResponseDto;
import edu.axboot.controllers.pmsDto.guest.GuestResponseDto;
import edu.axboot.domain.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GuestService extends BaseService<Guest, Long> {

    private final GuestRepository guestRepository;

    public List<GuestResponseDto> getList(RequestParams<GuestResponseDto> requestParams) {
        String guestNm = requestParams.getString("guestNm", "");
        String guestTel = requestParams.getString("guestTel", "");
        String email = requestParams.getString("email", "");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(guestNm)) {
            builder.and(qGuest.guestNm.contains(guestNm));
        }
        if (isNotEmpty(guestTel)) {
            builder.and(qGuest.guestTel.contains(guestTel));
        }
        if (isNotEmpty(email)) {
            builder.and(qGuest.email.contains(email));
        }

        List<Guest> entitis = select()
                .from(qGuest)
                .where(builder)
                .orderBy(qGuest.guestNm.asc())
                .fetch();

        return entitis.stream()
                .map(GuestResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GuestResponseDto findById(Long id) {
        Guest guest = guestRepository.findOne(id);

        if (guest == null) {
            throw new IllegalArgumentException("해당 투숙객 정보가 없습니다. id=" + id);
        }

        return new GuestResponseDto(guest);
    }

    @Transactional(readOnly = true)
    public List<GuestListResponseDto> findBy(String srchGuestNm, String srchGuestTel, String srchEmail) {
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(srchGuestNm)) { builder.and(qGuest.guestNm.like("%" + srchGuestNm +"%")); }

        if (isNotEmpty(srchGuestTel)) { builder.and(qGuest.guestTel.like("%" + srchGuestTel +"%")); }

        if (isNotEmpty(srchEmail)) { builder.and(qGuest.email.like("%" + srchEmail +"%")); }

        List<Guest> entities = select().select(
                Projections.fields(Guest.class,
                        qGuest.id, qGuest.guestNm, qGuest.guestTel, qGuest.email, qGuest.gender, qGuest.brth, qGuest.langCd
                ))
                .from(qGuest)
                .where(builder)
                .orderBy(qGuest.guestNm.asc())
                .fetch();

        return entities.stream()
                .map(GuestListResponseDto::new)
                .collect(Collectors.toList());
    }

   /* public List<Guest> getList(RequestParams<Guest> requestParams) {
        String guestNm = requestParams.getString("guestNm", "");
        String guestTel = requestParams.getString("guestTel", "");
        String email = requestParams.getString("email", "");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(guestNm)) {
            builder.and(qGuest.guestNm.contains(guestNm));
        }
        if (isNotEmpty(guestTel)) {
            builder.and(qGuest.guestTel.like("%" + guestTel +"%"));
        }
        if (isNotEmpty(email)) {
            builder.and(qGuest.email.like(email + "%"));
        }

        List<Guest> list = select()
                .from(qGuest)
                .where(builder)
                .orderBy(qGuest.guestNm.asc())
                .fetch();

        return list;
    }

    @Transactional
    public long save(GuestSaveRequestDto saveDto) {
        return guestRepository.save(saveDto.toEntity()).getId();
    }

    @Transactional
    public long update(GuestSaveRequestDto saveDto) {
        return guestRepository.save(saveDto.toEntity()).getId();
    }

    @Transactional
    public Long update(GuestUpdateRequestDto updateDto) {
        Guest guest = guestRepository.findOne(updateDto.getId());

        if (guest == null) {
            throw new IllegalArgumentException("해당 투숙객 정보가 없습니다. id=" + updateDto.getId());
        }

        // JPA 영속성 컨텍스트 사용 (엔티티를 영구 저장하는 환경)
        guest.update(updateDto.getGuestTel(), updateDto.getEmail(), updateDto.getRmk());

        return guest.getId();
    }



    @Transactional(readOnly = true)
    public List<GuestListResponseDto> findBy(String srchGuestNm, String srchGuestTel, String srchEmail) {
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(srchGuestNm)) {
            builder.and(qGuest.guestNm.like("%" + srchGuestNm +"%"));
        }

        if (isNotEmpty(srchGuestTel)) {
            builder.and(qGuest.guestTel.like("%" + srchGuestTel +"%"));
        }

        if (isNotEmpty(srchEmail)) {
            builder.and(qGuest.email.like("%" + srchEmail +"%"));
        }

        List<Guest> entities = select().select(
                Projections.fields(Guest.class,
                        qGuest.id, qGuest.guestNm, qGuest.guestTel, qGuest.email, qGuest.gender, qGuest.brth, qGuest.langCd
                ))
                .from(qGuest)
                .where(builder)
                .orderBy(qGuest.guestNm.asc())
                .fetch();

        return entities.stream()
                .map(GuestListResponseDto::new)
                .collect(Collectors.toList());
    }*/

}