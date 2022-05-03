package com.github.afezeria.serialtask;

import org.gradle.api.services.BuildService;
import org.gradle.api.services.BuildServiceParameters;

import javax.inject.Inject;

/**
 * @author afezeria
 */
public class SerialTaskService implements BuildService<SerialTaskService.Params> {

    @Override
    public Params getParameters() {
        return new Params();
    }

    static class Params implements BuildServiceParameters {
        @Inject
        public Params() {
        }
    }
}
