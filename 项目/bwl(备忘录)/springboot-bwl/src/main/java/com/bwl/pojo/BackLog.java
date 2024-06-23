package com.bwl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackLog {
    private int id;
    private int ownId;
    private String text;
    private int state;
    private int del;
}
