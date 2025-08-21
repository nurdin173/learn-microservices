package id.co.learn.core.common.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter @Setter
public class ReferenceBaseWrapper extends AuditableBaseWrapper {
    @Serial
    private static final long serialVersionUID = 1698964356043316460L;

    private Integer version;
    private Boolean deleted;
}
