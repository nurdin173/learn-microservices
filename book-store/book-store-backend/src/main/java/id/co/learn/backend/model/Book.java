package id.co.learn.backend.model;

import id.co.learn.core.common.model.ReferenceBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book extends ReferenceBase {
    private String title;
    private String author;
    private String publisher;
    private Boolean available;
    private BigDecimal price;
}
