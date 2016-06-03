package com.talentica.defaultNamespace.knowledge.dataType;

import com.talentica.graphite.store.api.annotation.ClassNode;
import com.talentica.graphite.store.api.annotation.ObjectNode;

/**
 * Created by aravindp on 1/6/16.
 */
@ClassNode
public class Email extends ObjectNode{
    public Email(String label) {
        super(label);
    }
}
