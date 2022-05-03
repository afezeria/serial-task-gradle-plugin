package com.github.afezeria.serialtask;

import java.util.List;
import java.util.Map;

/**
 * @author afezeria
 */
public interface SerialTaskExtension {

    Map<String, List<String>> getGroups();

    /**
     * @param task String task name
     */
    void set(String task);

    /**
     * @param group String group name
     * @param task  String task name
     */
    void set(String group, String task);

    String DEFAULT_GROUP_NAME = "defaultSerialGroup";
    String NAME = "serialTask";
    String DEFAULT_SERVICE_PREFIX = "defaultServicePrefix_";
}
