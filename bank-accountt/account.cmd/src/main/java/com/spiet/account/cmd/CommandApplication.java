package com.spiet.account.cmd;

import com.spiet.account.cmd.api.commands.CloseAccountCommand;
import com.spiet.account.cmd.api.commands.DepositFundsAccountCommand;
import com.spiet.account.cmd.api.commands.OpenAccountComands;
import com.spiet.account.cmd.api.commands.WithDrawFundsCommand;
import com.spiet.account.cmd.infra.CommandHandler;
import com.spiet.cqrs.core.infra.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {


	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(OpenAccountComands.class, commandHandler::handle);
		commandDispatcher.registerHandler(DepositFundsAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(WithDrawFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
	}

}
