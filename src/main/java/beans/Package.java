package beans;

public class Package {
    public static final int EXIT = -1;
    public static final int CREATE_ROOM = 1;
    public static final int CREATE_SUC = 11;
    public static final int CREATE_FAL = 12;
    public static final int CONNECT_ROOM = 2;
    public static final int CONNECT_SUC = 21;
    public static final int CONNECT_FAL = 22;
    public static final int LUOZI = 100;
    public static final int GAME_START = 101;

    int type;
    String roomName;
    boolean isWhite;
    int x;
    int y;
    String other;

    @Override
    public String toString() {
        return "Package{" +
                "type=" + type +
                ", roomName='" + roomName + '\'' +
                ", isWhite=" + isWhite +
                ", x=" + x +
                ", y=" + y +
                ", other='" + other + '\'' +
                '}';
    }

    public Package(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
