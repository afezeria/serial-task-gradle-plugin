package io.github.afezeria.serialtask;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.provider.Provider;

import java.util.List;
import java.util.Map;

/**
 * @author afezeria
 */
public class SerialTaskPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        SerialTaskExtension extension = project.getExtensions().create(SerialTaskExtension.class, SerialTaskExtension.NAME, SerialTaskExtensionImpl.class);
        project.afterEvaluate((p) -> {
            for (Map.Entry<String, List<String>> entry : extension.getGroups().entrySet()) {
                String group = entry.getKey();
                List<String> taskNameList = entry.getValue();
                Provider<SerialTaskService> serviceProvider = project.getGradle().getSharedServices().registerIfAbsent(
                        SerialTaskExtension.DEFAULT_SERVICE_PREFIX + group,
                        SerialTaskService.class,
                        (it) -> it.getMaxParallelUsages().set(1));
                for (String name : taskNameList) {
                    for (Task task : project.getTasksByName(name, true)) {
                        task.usesService(serviceProvider);
                    }
                }
            }
        });
    }
}
