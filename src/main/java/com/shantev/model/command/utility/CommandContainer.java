package com.shantev.model.command.utility;

import com.shantev.model.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    public static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("log_in", new LoginCommand());
        commandMap.put("sign_up", new RegisterCommand());
        commandMap.put("log_out", new LogoutCommand());
        commandMap.put("register_for_event", new RegisterForEventCommand());
        commandMap.put("get_users", new ListUsersCommand());
        commandMap.put("incorrect_command", new IncorrectCommand());
        commandMap.put("block_user", new BlockUserCommand());
        commandMap.put("unblock_user", new UnblockUserCommand());
        commandMap.put("show_my_account_info_page", new ShowAccountInfoPage());
        commandMap.put("show_change_account_info_page", new ShowChangeAccountInfoPage());
        commandMap.put("change_my_info", new ChangeAccountInfoCommand());
        commandMap.put("set_admin", new SetAdminCommand());
        commandMap.put("delete_admin", new DeleteAdminCommand());
        commandMap.put("set_manager", new SetManagerCommand());
        commandMap.put("delete_manager", new DeleteManagerCommand());
        commandMap.put("set_speaker", new SetSpeakerCommand());
        commandMap.put("delete_speaker", new DeleteSpeakerCommand());
        commandMap.put("show_add_event_page", new ShowAddEventPageCommand());
        commandMap.put("add_event", new AddEventCommand());
        commandMap.put("get_events", new GetEventsCommand());
        commandMap.put("delete_event", new DeleteEventCommand());
        commandMap.put("show_change_event_info_page", new ShowChangeEventInfoPage());
        commandMap.put("change_event", new ChangeEventCommand());
        commandMap.put("show_users_joined_to_event", new ShowUsersJoinedToEvent());
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
