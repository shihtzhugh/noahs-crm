package com.shihtzhugh.service;

import com.shihtzhugh.model.Tool;
import com.shihtzhugh.repository.ToolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ToolService {

    private final ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<Tool> findAllTools() {
        return toolRepository.findAll();
    }

    public Tool findToolById(Long id) {
        return toolRepository.findById(id).orElse(null);
    }

    public Tool saveTool(Tool tool) {
        return toolRepository.save(tool);
    }
}
