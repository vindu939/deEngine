package com.talentica.defaultNamespace.knowledge;

import com.talentica.defaultNamespace.knowledge.dataType.Email;
import com.talentica.defaultNamespace.knowledge.dataType.FreeText;
import com.talentica.graphite.store.api.annotation.ClassNode;
import com.talentica.graphite.store.api.annotation.VerbInput;
import com.talentica.graphite.store.api.annotation.VerbNode;


/**
 * Created by aravindp on 23/5/16.
 */
@ClassNode
public class DefaultActions {

    @VerbNode(restEndpoint = "/createClass", endpointType = "POST", synonym = "")
    public void Object(
            @VerbInput(userQuery = "Name of the object?", label = "label") FreeText name
    ){}

    @VerbNode(restEndpoint = "/createProperty", endpointType = "POST", synonym = "")
    public void Property(
            @VerbInput(userQuery = "Name of the property?", label = "label") FreeText name
    ){}

    @VerbNode(restEndpoint = "/addProperty", endpointType = "POST", synonym = "")
    public void addProperty(
            @VerbInput(userQuery = "Name of the property?", label = "property") DomainProperty property,
            @VerbInput(userQuery = "Name of the object?", label = "object") DomainClass object
    ){}

    // Command Actions
    @VerbNode(restEndpoint = "/oauth/token", endpointType = "GET", synonym = "")
    public void login(
            @VerbInput(userQuery = "Enter email", label = "username") Email email,
            @VerbInput(userQuery = "Enter password", label = "password") FreeText password,
            @VerbInput(userQuery = "", isOptional = true, label = "grant_type", value = "password") FreeText grantType
    ){}
}
