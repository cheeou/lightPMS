package edu.axboot.domain.hotel.reservation.memo;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class RevMemoService extends BaseService<RevMemo, Long> {
    private RevMemoRepository revMemoRepository;

    @Inject
    public RevMemoService(RevMemoRepository revMemoRepository) {
        super(revMemoRepository);
        this.revMemoRepository = revMemoRepository;
    }

    public List<RevMemo> gets(RequestParams<RevMemo> requestParams) {
        return findAll();
    }
}