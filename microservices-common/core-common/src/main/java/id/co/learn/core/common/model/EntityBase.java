package id.co.learn.core.common.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@MappedSuperclass
@Getter @Setter
public class EntityBase implements Serializable {
    @Serial
    private static final long serialVersionUID = -917598374644711216L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 50)
    @Id
    private Long id;
    @Column(length = 500)
    private String description;
}
