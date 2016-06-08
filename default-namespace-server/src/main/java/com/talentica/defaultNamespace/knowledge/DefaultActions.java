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
    public void Class(
            @VerbInput(userQuery = "Name of the class?", label = "label") FreeText name
    ){}

    @VerbNode(restEndpoint = "/createProperty", endpointType = "POST", synonym = "")
    public void Property(
            @VerbInput(userQuery = "Name of the property?", label = "label") FreeText name
    ){}

    @VerbNode(restEndpoint = "/addProperty", endpointType = "POST", synonym = "")
    public void addProperty(
            @VerbInput(userQuery = "Name of the property?", label = "property") DomainProperty property,
            @VerbInput(userQuery = "Name of the class?", label = "clazz") DomainClass object
    ){}

    @VerbNode(restEndpoint = "/createAction", endpointType = "POST", synonym = "")
    public void Action(
            @VerbInput(userQuery = "Name of the action?", label = "label") FreeText name
    ){}

    @VerbNode(restEndpoint = "/addActionAttributes", endpointType = "POST", synonym = "")
    public void ActionAttributes(
            @VerbInput(userQuery = "Enter rest endpoint", label = "restEndpoint") FreeText restEndpoint,
            @VerbInput(userQuery = "Enter endpoint type(GET/POST)", label = "endpointType") FreeText endpointType,
            /*@VerbInput(userQuery = "Enter synonym(comma separated)", label = "synonym", isOptional = true) FreeText synonym,*/
            @VerbInput(userQuery = "Enter name of action?", label = "action") DomainAction action

    ){}

    @VerbNode(restEndpoint = "/addInput", endpointType = "POST", synonym = "")
    public void Input(
            @VerbInput(userQuery = "Enter input name", label = "label") FreeText name,
            @VerbInput(userQuery = "Enter name of class", label = "clazz") DomainClass domainClass,
            @VerbInput(userQuery = "Enter name of action?", label = "action") DomainAction action

    ){}

    @VerbNode(restEndpoint = "/addInputAttributes", endpointType = "POST", synonym = "")
    public void InputAttributes(
            @VerbInput(userQuery = "Enter userQuery", label = "userQuery") FreeText userQuery,
            @VerbInput(userQuery = "Enter isOptional(true/false)", label = "isOptional") FreeText isOptional,
            @VerbInput(userQuery = "Enter input name", label = "input") DomainInput input,
            @VerbInput(userQuery = "Enter action name", label = "action") DomainAction action,
            @VerbInput(userQuery = "Enter class name", label = "clazz") DomainClass domainClass
    ){}

    @VerbNode(restEndpoint = "/createObject", endpointType = "POST", synonym = "")
    public void Object(
            @VerbInput(userQuery = "Enter object name", label = "value") FreeText object,
            @VerbInput(userQuery = "Enter class name", label = "clazz") DomainClass domainClass
    ){}
    // Command Actions
    @VerbNode(restEndpoint = "/oauth/token", endpointType = "POST", synonym = "")
    public void login(
            @VerbInput(userQuery = "Enter email", label = "username") Email email,
            @VerbInput(userQuery = "Enter password", label = "password") FreeText password,
            @VerbInput(userQuery = "", isOptional = true, label = "grant_type", value = "password") FreeText grantType
    ){}
}
