package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        if (storage.containsKey(model.getId())) {
            return;
        }
        storage.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
            storage.put(id, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T value = storage.remove(id);
        return value != null;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}