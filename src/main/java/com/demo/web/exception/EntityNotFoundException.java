package com.demo.web.exception;




import com.demo.web.exception.handle.ServiceError;

import java.util.LinkedHashMap;

public class EntityNotFoundException extends ServiceException{

    public EntityNotFoundException() {
        super(ServiceError.ENTITY_NOT_FOUND, null, null);
    }

    public EntityNotFoundException(String entityName, String entityId) {
        super(ServiceError.ENTITY_NOT_FOUND, null, buildParams(entityName, entityId));
    }

    private static LinkedHashMap<String, Object> buildParams (String entityName, String entityId) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("entityName", entityName);
        params.put("entityId", entityId);
        return params;
    }

}
