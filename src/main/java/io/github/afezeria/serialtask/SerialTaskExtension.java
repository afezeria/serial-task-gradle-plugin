package io.github.afezeria.serialtask;

import java.util.List;
import java.util.Map;

/**
 * @author afezeria
 */
public interface SerialTaskExtension {

    /**
     * group info
     */
    Map<String, List<String>> getGroups();

    /**
     * Specifies the name of the task that needs to be executed serially
     * @param task String task name
     */
    void set(String task);

    /**
     * Specify the name and group name of the task that needs to be executed serially, only the tasks in the same group will be serialized
     * @param group String group name
     * @param task  String task name
     */
    void set(String group, String task);

    String DEFAULT_GROUP_NAME = "defaultSerialGroup";
    String NAME = "serialTask";
    String DEFAULT_SERVICE_PREFIX = "defaultServicePrefix_";
}
