package ru.job4j.question;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, User> currentMap = current.stream().collect(Collectors.toMap(User::getId, user -> user));
        for (User prevUser : previous) {
            User curUser = currentMap.get(prevUser.getId());
            if (curUser == null) {
                rsl.setDeleted(rsl.getDeleted() + 1);
                continue;
            }
            if (!Objects.equals(prevUser.getName(), curUser.getName())) {
                rsl.setChanged(rsl.getChanged() + 1);
            }
            currentMap.remove(prevUser.getId());
        }
        rsl.setAdded(currentMap.size());
        return rsl;
    }
}