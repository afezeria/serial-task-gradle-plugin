package io.github.afezeria.serialtask


import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

import java.time.LocalDateTime

class SerialTaskPluginFunctionalTest {
    @TempDir
    File testProjectDir
    File buildFile

    @BeforeEach
    void setup() {
        buildFile = new File(testProjectDir, 'build.gradle')
        buildFile << """
plugins {
    id("com.github.afezeria.serial-task")
}
subprojects {
    apply{
        plugin("com.github.afezeria.serial-task")
    }

    task a {
        doLast {
            println "==== start \${LocalDateTime.now()}"
            Thread.sleep(5000)
            println "==== end   \${LocalDateTime.now()}"
        }
    }
}
        """
        def settingsFile = new File(testProjectDir, 'settings.gradle')
        settingsFile << """
rootProject.name = 'test'
include('sub-1','sub-2')
"""
    }

    @Test
    void "test"() {
        def result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withArguments('a', '--parallel')
            .withPluginClasspath()
            .build()

        def logs = result.output.split("\n")
            .findAll { it.startsWithAny("==== start ", "==== end ") }
        def start = LocalDateTime.parse(logs.first().substring(11))
        def end = LocalDateTime.parse(logs.last().substring(11))
        assert start.plusSeconds(10).isAfter(end)

        buildFile<<"""
serialTask{
set('a')
}
"""
        result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withArguments('a', '--parallel')
            .withPluginClasspath()
            .build()

        logs = result.output.split("\n")
            .findAll { it.startsWithAny("==== start ", "==== end ") }
        start = LocalDateTime.parse(logs.first().substring(11))
        end = LocalDateTime.parse(logs.last().substring(11))
        assert start.plusSeconds(10).isBefore(end)

    }
}