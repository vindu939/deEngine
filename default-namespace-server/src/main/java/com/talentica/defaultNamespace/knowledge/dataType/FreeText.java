package com.talentica.defaultNamespace.knowledge.dataType;

import com.talentica.graphite.store.api.annotation.ClassNode;
import com.talentica.graphite.store.api.annotation.ObjectNode;

/**
 * Created by aravindp on 24/5/16.
 */
@ClassNode
public class FreeText extends ObjectNode{
    public FreeText(String label) {
        super(label);
    }
}
