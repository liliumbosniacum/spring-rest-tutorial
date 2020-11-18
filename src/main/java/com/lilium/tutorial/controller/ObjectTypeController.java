package com.lilium.tutorial.controller;

import com.lilium.tutorial.api.AbstractCRUDLApi;
import com.lilium.tutorial.api.ObjectTypeApi;
import com.lilium.tutorial.dto.ObjectTypeDTO;
import com.lilium.tutorial.entity.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/objecttypes")
public class ObjectTypeController extends AbstractCRUDLController<ObjectType, ObjectTypeDTO> {

    @Autowired
    public ObjectTypeController(final ObjectTypeApi api) {
        super(api);
    }
}
