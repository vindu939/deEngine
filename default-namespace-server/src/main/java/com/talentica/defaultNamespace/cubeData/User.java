package com.talentica.defaultNamespace.cubeData;

import com.talentica.graphite.store.api.annotation.ClassNode;
import com.talentica.graphite.store.api.annotation.ObjectNode;
import com.talentica.graphite.store.api.annotation.PropertyNode;

/**
 * Created by aravindp on 18/7/16.
 */
@ClassNode
public class User extends ObjectNode{
    public User(String label) {
        super(label);
    }

    @PropertyNode
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
