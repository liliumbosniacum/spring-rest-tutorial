package com.lilium.tutorial.controller;

import com.lilium.tutorial.api.AbstractCRUDLApi;
import com.lilium.tutorial.dto.BaseDTO;
import com.lilium.tutorial.entity.DistributedEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

public class AbstractCRUDLController<ENTITY extends DistributedEntity, DTO extends BaseDTO> {
    private final AbstractCRUDLApi<ENTITY, DTO> api;

    public AbstractCRUDLController(AbstractCRUDLApi<ENTITY, DTO> api) {
        this.api = api;
    }

    @PostMapping
    public DTO save(@RequestBody DTO dto) {
        return api.save(dto);
    }

    @GetMapping("/{id}")
    public DTO getById(@PathVariable Integer id) {
        return api.getById(id);
    }

    @GetMapping("/list")
    public List<DTO> list() {
        return api.list();
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return api.delete(id);
    }

    @GetMapping("/modified")
    public List<DTO> modified(@RequestParam("modifiedSince") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
        return api.modifiedSince(time);
    }
}