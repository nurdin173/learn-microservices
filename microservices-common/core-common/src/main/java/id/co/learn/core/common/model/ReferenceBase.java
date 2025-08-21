package id.co.learn.core.common.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@MappedSuperclass
@Getter @Setter
public class ReferenceBase extends AuditableBase {
    @Serial
    private static final long serialVersionUID = 7347831171722731137L;

    @Version
    private Integer version;
    private Boolean deleted;

    @PreUpdate
    public void preUpdate() {
        if (this.version == null) {
            this.version = 1;
            return;
        }
        this.version = this.getVersion() + 1;
    }
}
