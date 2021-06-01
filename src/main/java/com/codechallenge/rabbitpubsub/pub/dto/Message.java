package com.codechallenge.rabbitpubsub.pub.dto;

import com.codechallenge.rabbitpubsub.common.Branch;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Message implements Serializable {
    @NotNull
    private Branch destination;
    @NotBlank
    private String payload;
}
