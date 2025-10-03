package com.shihtzhugh.service;

import com.shihtzhugh.model.Tool;
import com.shihtzhugh.model.dto.ToolDto;
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

    public List<ToolDto> findAllTools() {
        return toolRepository.findAll()
                .stream()
                .map(ToolDto::from)
                .toList();
    }

    public ToolDto saveTool(ToolDto toolDto) {
        var tool = Tool.from(toolDto);
        return ToolDto.from(toolRepository.save(tool));
    }
}
