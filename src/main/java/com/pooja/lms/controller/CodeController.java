package com.pooja.lms.controller;

import com.pooja.lms.model.CodeRequest;
import com.pooja.lms.service.CodeExecutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/code")
@CrossOrigin(origins = "*")
public class CodeController {

    private final CodeExecutionService codeExecutionService;

    public CodeController(CodeExecutionService codeExecutionService) {
        this.codeExecutionService = codeExecutionService;
    }

    @PostMapping("/run")
    public ResponseEntity<Map<String, String>> runCode(
            @RequestBody CodeRequest request) {

        Map<String, String> response = new HashMap<>();

        String language = request.getLanguage();
        String code = request.getCode();

        if (language == null || language.trim().isEmpty()) {
            response.put("output", "Language is required.");
            return ResponseEntity.badRequest().body(response);
        }

        if (code == null || code.trim().isEmpty()) {
            response.put("output", "Code is required.");
            return ResponseEntity.badRequest().body(response);
        }

        if (language.equalsIgnoreCase("python")) {

            String output = codeExecutionService.executePython(code);

            response.put("output", output);

            return ResponseEntity.ok(response);
        }

        if (language.equalsIgnoreCase("java")) {

            response.put(
                    "output",
                    "Java sandbox will be connected next."
            );

            return ResponseEntity.ok(response);
        }

        response.put(
                "output",
                "Unsupported language: " + language
        );

        return ResponseEntity.badRequest().body(response);
    }
}