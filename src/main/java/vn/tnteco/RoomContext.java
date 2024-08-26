package vn.tnteco;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoomContext {
    private static CopyOnWriteArrayList<Room> rooms = new CopyOnWriteArrayList<>();

    public static void addRoom(Room room) {
        if (!checkExist(room.getRoomName())) {
            rooms.add(room);
        } else {
            throw new IllegalArgumentException("Room does already exist");
        }
    }

    public static boolean checkExist(String name) {
        return rooms.stream()
                .filter(s -> s.getRoomName().equals(name))
                .findFirst()
                .isPresent();
    }

    public static void removeRoom(Room room) {
        if (checkExist(room.getRoomName())) {
            rooms.remove(room);
        }
    }

    public static Room getRoomByName(String name) {
        return rooms.stream()
                .filter(s -> s.getRoomName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Room not exist")
                );
    }

    public static List<Room> getRooms() {
        return rooms;
    }

}
