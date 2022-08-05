package net.core.corediscordbot;

public enum Commands {

    PING("ping", "Ping pong"),
    INFO("info", "Information about core community"),
    HELP("help", "Help on how to use the bot");

    private final String name,description;

    Commands(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
