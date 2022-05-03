package io.github.afezeria.serialtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author afezeria
 */
public class SerialTaskExtensionImpl implements SerialTaskExtension {
    private final Map<String, List<String>> map = new HashMap<>();

    @Override
    public Map<String, List<String>> getGroups() {
        return map;
    }

    @Override
    public void set(String task) {
        map.computeIfAbsent(DEFAULT_GROUP_NAME, i -> new ArrayList<>()).add(task);
    }

    @Override
    public void set(String group, String task) {
        map.computeIfAbsent(group, i -> new ArrayList<>()).add(task);
    }
}
