package com.spiet.account.cmd.api.controller;

import com.spiet.account.cmd.api.commands.OpenAccountComands;
import com.spiet.account.cmd.api.dto.OpenAccountDTO;
import com.spiet.account.common.dto.BaseResponse;
import com.spiet.cqrs.core.infra.CommandDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/openBankAccount")
public class OpenAccountController {
    private final Logger logger = LoggerFactory.getLogger(OpenAccountController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountComands comands) throws Exception {
        var id = UUID.randomUUID().toString();
        comands.setId(id);

        commandDispatcher.send(comands);

        return new ResponseEntity<>(new OpenAccountDTO("Bank account reqest completed", id), HttpStatus.ACCEPTED);
    }
}
