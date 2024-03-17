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
public class RoleContent implements Serializable {
    private static final long serialVersionUID = 73098124L;
    String role;
    String content;
}
