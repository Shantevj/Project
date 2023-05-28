package com.shantev.model.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    public static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("log_in", new LoginCommand());
        commandMap.put("sign_up", new RegisterCommand());
        commandMap.put("log_out", new LogoutCommand());
        commandMap.put("register_for_event", new RegisterForEventCommand());
        commandMap.put("select_users", new ListUsersCommand());
        commandMap.put("incorrect_command", new IncorrectCommand());
        commandMap.put("block_user", new BlockUserCommand());
        commandMap.put("unblock_user", new UnblockUserCommand());
//        commandMap.put("log_in", new LoginCommand());
//        commandMap.put("log_in", new LoginCommand());
    }

    public static Command getCommand(String commandName) {
        if(commandName != null && commandMap.containsKey(commandName)) {
            return commandMap.get(commandName);
        }
        return commandMap.get("incorrect_command");
    }
}
