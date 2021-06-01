package com.codechallenge.rabbitpubsub.sub.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message_log")
public class MessageLog implements Serializable {

    @Id
    @Type(type="pg-uuid")
    @Column(name = "id")
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "store_datetime")
    private Date storeDateTime;

    @Column(name = "payload",length = 2048)
    private String payload;
}
