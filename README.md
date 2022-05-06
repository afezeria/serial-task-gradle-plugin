# serial-task

This gradle plugin allows you to execute parallel tasks serially without setting `org.gradle.workers.max=1`

## Install

```groovy
plugins {
    id 'io.github.afezeria.serial-task' version '1.0'
}
```

## Usage

You can provide task names by plugin extension

```groovy
// build.gradle of root project
serialTask {
    set 'test'
}
```

After plugin applied, task-a and task-b of all projects will be executed in series.

You can also specify the group name when providing the task name:

```groovy
// build.gradle of root project
serialTask {
    set 'group1', 'task-a'
    set 'group2', 'task-b'
}
```

At this time, only tasks in the same group will be executed serially.
