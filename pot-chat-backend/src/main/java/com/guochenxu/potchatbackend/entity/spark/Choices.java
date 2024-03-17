package com.guochenxu.potchatbackend.entity.spark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Choices implements Serializable {
    private static final long serialVersionUID = 7304219L;
    List<Text> text;
}