package com.spiet.cqrs.core.infra;

import com.spiet.cqrs.core.commands.BaseCommand;
import com.spiet.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> commandHandlerMethod);
    void send(BaseCommand baseCommand);
}
