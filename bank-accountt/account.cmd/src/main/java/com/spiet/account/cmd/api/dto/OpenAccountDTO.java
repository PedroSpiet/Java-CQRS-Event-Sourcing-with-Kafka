package com.spiet.account.cmd.api.dto;

import com.spiet.account.common.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountDTO extends BaseResponse {
    private String id;

    public OpenAccountDTO(String message, String id) {
        super(message);
        this.id = id;
    }
}
