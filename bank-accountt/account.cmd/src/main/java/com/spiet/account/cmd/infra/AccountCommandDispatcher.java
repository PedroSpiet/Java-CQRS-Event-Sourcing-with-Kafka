package com.spiet.account.cmd.infra;

import com.spiet.cqrs.core.commands.BaseCommand;
import com.spiet.cqrs.core.commands.CommandHandlerMethod;
import com.spiet.cqrs.core.infra.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> commandHandlerMethod) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(commandHandlerMethod);
    }

    @Override
    public void send(BaseCommand baseCommand) throws Exception {
        var handlers = routes.get(baseCommand.getClass());
        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send command to more than one handlers");
        }

        handlers.get(0).handler(baseCommand);
    }
}
