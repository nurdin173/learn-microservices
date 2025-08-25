package id.co.learn.backend.model;

import id.co.learn.core.common.model.ReferenceBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter @Setter
public class Category extends ReferenceBase {
    private String name;
}
