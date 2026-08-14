
package com.pooja.lms.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CodeExecutionService {

    public String executePython(String code) {

        if (code == null || code.isBlank()) {
            return "Code is empty.";
        }

        /*
         * Docker sandbox.
         *
         * The code is executed inside a temporary
         * Python container with:
         *
         * --network none
         * --memory 128m
         * --cpus 0.5
         * --pids-limit 64
         * --rm
         *
         * This prevents the container from having
         * normal network access and limits resources.
         */

        List<String> command = new ArrayList<>();

        command.add("docker");
        command.add("run");
        command.add("--rm");

        command.add("--network");
        command.add("none");

        command.add("--memory");
        command.add("128m");

        command.add("--cpus");
        command.add("0.5");

        command.add("--pids-limit");
        command.add("64");

        command.add("-i");

        command.add("python:3.12-alpine");

        command.add("python");

        command.add("-c");

        command.add(code);

        try {

            ProcessBuilder builder =
                    new ProcessBuilder(command);

            builder.redirectErrorStream(true);

            Process process =
                    builder.start();

            StringBuilder output =
                    new StringBuilder();

            try (
                    BufferedReader reader =
                            new BufferedReader(
                                    new InputStreamReader(
                                            process.getInputStream(),
                                            StandardCharsets.UTF_8
                                    )
                            )
            ) {

                String line;

                while ((line = reader.readLine()) != null) {

                    output.append(line)
                            .append(System.lineSeparator());

                    if (output.length() > 10000) {
                        process.destroyForcibly();
                        return "Output too large.";
                    }
                }
            }

            boolean finished =
                    process.waitFor(
                            10,
                            TimeUnit.SECONDS
                    );

            if (!finished) {

                process.destroyForcibly();

                return "Execution timed out.";
            }

            String result =
                    output.toString().trim();

            if (result.isEmpty()) {

                return "Program finished with no output.";
            }

            return result;

        } catch (Exception e) {

            return "Execution error: "
                    + e.getMessage();
        }
    }
}

