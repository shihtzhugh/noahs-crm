package com.shihtzhugh.controller;

import com.shihtzhugh.model.dto.ToolDto;
import com.shihtzhugh.service.ToolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tools")
public class ToolController {

    private ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    public ResponseEntity<List<ToolDto>> getAllTools() {
        return ResponseEntity.ok(toolService.findAllTools());
    }

    @PostMapping
    public ResponseEntity<ToolDto> createTool(@RequestBody ToolDto toolDto) {
        return ResponseEntity.ok(toolService.saveTool(toolDto));
    }
}
