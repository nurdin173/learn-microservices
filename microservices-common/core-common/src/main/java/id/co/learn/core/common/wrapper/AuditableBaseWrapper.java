package id.co.learn.core.common.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class AuditableBaseWrapper extends EntityBaseWrapper {
    private static final long serialVersionUID = 2232077551024723412L;

    private String createdBy;
    private String lastModifiedBy;
    private Date createdDate;
    private String lastModifiedDate;
}
