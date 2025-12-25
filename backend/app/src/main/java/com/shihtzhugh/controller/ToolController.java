package com.shihtzhugh.controller;

import com.shihtzhugh.model.Tool;
import com.shihtzhugh.model.dto.CreateToolRequest;
import com.shihtzhugh.model.dto.ToolResponse;
import com.shihtzhugh.repository.ToolRepository;
import com.shihtzhugh.service.ToolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tools")
public class ToolController {

    private static final Logger logger = LoggerFactory.getLogger(ToolController.class);

    private final ToolService toolService;
    private final String uploadDirectory;
    private final ToolRepository toolRepository;

    public ToolController(ToolService toolService, @Value("${file.upload.dir:./uploads}") String uploadDirectory, ToolRepository toolRepository) {
        this.toolService = toolService;
        this.uploadDirectory = uploadDirectory;
        this.toolRepository = toolRepository;
    }

    @GetMapping
    public ResponseEntity<List<ToolResponse>> getAllTools() {
        var toolList = toolService.findAllTools();
        return ResponseEntity.ok(
                toolList.stream()
                        .map(ToolResponse::from)
                        .toList());
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<ToolResponse> uploadToolImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        String fileName = "attachment-" + UUID.randomUUID() + ".jpg";
        Path filePath = Path.of(uploadDirectory, fileName);
        try {
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var tool = toolService.findToolById(id);
        tool.setImageUri("/uploads/" + fileName);
        toolService.saveTool(tool);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ToolResponse> createTool(@RequestBody CreateToolRequest createToolRequest) {
        var tool = Tool.from(createToolRequest);
        var savedTool = toolService.saveTool(tool);
        return ResponseEntity.ok(ToolResponse.from(savedTool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToolResponse> deleteTool(@PathVariable Long id) {
        var tool = toolService.findToolById(id);
        var imageUri = tool.getImageUri();
        if (imageUri != null) {
            try {
                Files.delete(Path.of(".", imageUri));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        toolRepository.delete(toolService.findToolById(id));
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<ToolResponse> handleException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
