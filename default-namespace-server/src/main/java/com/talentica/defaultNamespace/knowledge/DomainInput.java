package com.talentica.defaultNamespace.knowledge;

import com.talentica.graphite.store.api.annotation.ClassNode;
import com.talentica.graphite.store.api.annotation.ObjectNode;

/**
 * Created by aravindp on 24/5/16.
 */
@ClassNode
public class DomainInput extends ObjectNode{
    public DomainInput(String label) {
        super(label);
    }
}
