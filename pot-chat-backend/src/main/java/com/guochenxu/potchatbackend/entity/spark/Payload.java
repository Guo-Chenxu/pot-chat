package com.guochenxu.potchatbackend.entity.spark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payload implements Serializable {
    private static  final long serialVersionUID = 78902134L;
    Choices choices;
}