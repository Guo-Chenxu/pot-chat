package com.guochenxu.potchatbackend.entity.spark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//返回的json结果拆解
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JsonParse implements Serializable {
    private static final long serialVersionUID = 4319820L;
    Header header;
    Payload payload;
}

