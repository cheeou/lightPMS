package edu.axboot.controllers.pmsDto.memo;

import edu.axboot.domain.hotel.reservation.memo.RevMemo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RevMemoSaveRequestDto {
    private Long id;
    private String memoCn;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
    public RevMemoSaveRequestDto(Long id, String memoCn, boolean __created__, boolean __modified__, boolean __deleted__) {
        this.id = id;
        this.memoCn = memoCn;
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;
    }

    public RevMemo toEntity() {
        return RevMemo.builder()
                .id(id)
                .memoCn(memoCn)
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .build();
    }
}
