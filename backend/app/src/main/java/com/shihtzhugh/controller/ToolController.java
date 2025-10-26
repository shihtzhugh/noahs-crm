package com.shihtzhugh.controller;

import com.shihtzhugh.model.Tool;
import com.shihtzhugh.model.dto.ToolDto;
import com.shihtzhugh.service.ToolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tools")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    public ResponseEntity<List<ToolDto>> getAllTools() {
        var toolList = toolService.findAllTools();
        return ResponseEntity.ok(
                toolList.stream()
                        .map(ToolDto::from)
                        .toList());
    }

    @PostMapping
    public ResponseEntity<ToolDto> createTool(@RequestBody ToolDto toolDto) {
        var tool = Tool.from(toolDto);
        var savedTool = toolService.saveTool(tool);
        return ResponseEntity.ok(ToolDto.from(savedTool));
    }
}
