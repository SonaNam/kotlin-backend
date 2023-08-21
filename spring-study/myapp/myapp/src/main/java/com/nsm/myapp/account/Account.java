package com.nsm.myapp.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data     //getter,setter , 생성자등 자동으로 만들어줌, 컴파일(실행할때) 시점에
@Builder
@AllArgsConstructor

public class Account {
    private String ano;
    private String ownerName;
    private long balance;
    private long createTime;
}
