
package com.app.medStock.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gusta
 */
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public class MasterEntity implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(updatable = false, insertable = false)
    private LocalDateTime created;
    
    @Version
    @Getter
    @Setter
    private int version;
}
