package beans;

import utils.Connection;

import java.net.Socket;

public class Room {
    private String name;
    private Connection host;
    private Connection client;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Connection getHost() {
        return host;
    }

    public void setHost(Connection host) {
        this.host = host;
    }

    public Connection getClient() {
        return client;
    }

    public void setClient(Connection client) {
        this.client = client;
    }
}
