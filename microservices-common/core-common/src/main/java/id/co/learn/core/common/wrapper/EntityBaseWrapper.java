package id.co.learn.core.common.wrapper;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
public class EntityBaseWrapper implements Serializable {
    @Serial
    private static final long serialVersionUID = -9065524793263943014L;
    private Long id;
    private String description;
}
