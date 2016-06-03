package com.talentica.defaultNamespace.web.controllers;

import com.talentica.defaultNamespace.knowledge.DomainAction;
import com.talentica.defaultNamespace.knowledge.DomainClass;
import com.talentica.defaultNamespace.knowledge.DomainInput;
import com.talentica.defaultNamespace.knowledge.DomainProperty;
import com.talentica.defaultNamespace.web.objects.SkeletonRequest;
import com.talentica.graphite.store.StoreResources;
import com.talentica.graphite.store.api.exception.ObjectWriteException;
import com.talentica.graphite.store.atom.*;
import com.talentica.graphite.store.atom.system.SystemVocabulary;
import com.talentica.graphite.store.atom.system.VerbAtom;
import com.talentica.graphite.store.atom.system.VerbInputAtom;
import com.talentica.graphite.store.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by aravindp on 23/5/16.
 */
@RestController
@RequestMapping("/")
public class SkeletonController {

    // Default Store Resources
    @Autowired
    private StoreResources defaultStoreResources;

    @Autowired
    private ClassAtomStore defaultClassAtomStore;
    @Autowired
    private ObjectAtomStore defaultObjectAtomStore;
    @Autowired
    private PropertyAtomStore defaultPropertyAtomStore;

    // User opted Store Resources
    @Autowired
    private StoreResources optedStoreResources;

    @Autowired
    private ClassAtomStore optedClassAtomStore;
    @Autowired
    private ObjectAtomStore optedObjectAtomStore;
    @Autowired
    private PropertyAtomStore optedPropertyAtomStore;

    @RequestMapping(value = "createClass", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Atom createClass(@RequestBody SkeletonRequest data){
        Atom clazzAtom = null;
        ObjectAtom objectAtom = null;
        String label = data.getLabel();
        try {
            clazzAtom = optedClassAtomStore.getOrCreate(label);
            DomainClass domainClass = new DomainClass(label);
            defaultObjectAtomStore.writeObjectAtom(domainClass);
            defaultStoreResources.flushAll();
            optedStoreResources.flushAll();
        } catch (UnSupportedTypeException e) {
            e.printStackTrace();
        } catch (InvalidStoreException e) {
            e.printStackTrace();
        } catch (InvalidAtomException e) {
            e.printStackTrace();
        } catch (StatementStoreException e) {
            e.printStackTrace();
        } catch (StoreException e) {
            e.printStackTrace();
        } catch (ObjectWriteException e) {
            e.printStackTrace();
        } finally {
            if(clazzAtom != null && objectAtom != null) {
                defaultStoreResources.flushAll();
                optedStoreResources.flushAll();
            }
        }

        return clazzAtom;
    }

    @RequestMapping(value = "createProperty", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Atom createProperty(@RequestBody SkeletonRequest data){
        Atom clazzAtom = null;
        String label = data.getLabel();
        try {
            clazzAtom = optedPropertyAtomStore.getOrCreate(label);
            DomainProperty domainProperty = new DomainProperty(label);
            defaultObjectAtomStore.writeObjectAtom(domainProperty);
        } catch (InvalidAtomException e) {
            e.printStackTrace();
        } catch (UnSupportedTypeException e) {
            e.printStackTrace();
        } catch (StoreException e) {
            e.printStackTrace();
        } catch (InvalidStoreException e) {
            e.printStackTrace();
        } catch (ObjectWriteException e) {
            e.printStackTrace();
        } finally {
            if(clazzAtom != null) {
                defaultStoreResources.flushAll();
                optedStoreResources.flushAll();
            }
        }

        return clazzAtom;
    }

    @RequestMapping(value = "addProperty", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public PropertyAtom addProperty(@RequestBody SkeletonRequest data){
        PropertyAtom propertyAtom = null;
        String propertyName = data.getProperty();
        String objectName = data.getObject();
        try {
            ClassAtom domainAtom = (ClassAtom) optedClassAtomStore.getOrCreate(objectName);
            ClassAtom rangeAtom = (ClassAtom) optedClassAtomStore.getOrCreate(SystemVocabulary.SHINGLE_ATOM_CLAZZ_URI);

            propertyAtom = optedPropertyAtomStore.getOrCreate(propertyName,domainAtom.getUri(), rangeAtom.getUri());
        } catch (UnSupportedTypeException e) {
            e.printStackTrace();
        } catch (StoreException e) {
            e.printStackTrace();
        } catch (InvalidAtomException e) {
            e.printStackTrace();
        } finally {
            if (propertyAtom != null){
                optedStoreResources.flushAll();
            }
        }
        return propertyAtom;
    }

    @RequestMapping(value = "createAction", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public VerbAtom createAction(@RequestBody SkeletonRequest data){
        VerbAtom verbAtom = null;
        String label = data.getLabel();
        try {
            URI verbAtomUri = URI.create(SystemVocabulary.GRAPHITE.toString().concat("/Action#").concat(label));
            verbAtom = new VerbAtom(-1, verbAtomUri, label);
            optedClassAtomStore.saveVerbAtom(verbAtom);
            DomainAction domainAction = new DomainAction(label);
            defaultObjectAtomStore.writeObjectAtom(domainAction);
        } catch (UnSupportedTypeException e) {
            e.printStackTrace();
        } catch (InvalidStoreException e) {
            e.printStackTrace();
        } catch (StoreException e) {
            e.printStackTrace();
        } catch (ObjectWriteException e) {
            e.printStackTrace();
        } finally {
            if(verbAtom != null) {
                defaultStoreResources.flushAll();
                optedStoreResources.flushAll();
            }
        }
        return verbAtom;
    }

    @RequestMapping(value = "addActionAttributes", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public VerbAtom addActionAttributes(@RequestBody SkeletonRequest data){
        VerbAtom verbAtom = null;

        String action = data.getAction();
        String restEndpoint = data.getRestEndpoint();
        String endpointType = data.getEndpointType();
        String synonym = data.getSynonym();

        try {

            URI verbAtomUri = URI.create(SystemVocabulary.GRAPHITE.toString().concat("/Action#").concat(action));
            verbAtom = new VerbAtom(-1, verbAtomUri, action);

            // RestEndpoint
            verbAtom.addAttribute(SystemVocabulary.VERB_REST_ENDPOINT.toString(), restEndpoint);

            // Endpoint type i.e GET, POST, etc
            verbAtom.addAttribute(SystemVocabulary.VERB_REST_ENDPOINT_TYPE.toString(), endpointType);

            // Synonyms
            final String synonyms[] = synonym.split(",");
            if(synonyms.length > 0 ){
                for(String label : synonyms){
                    verbAtom.addAttribute(SystemVocabulary.GRAPHITE_LABEL.toString(), label);
                    optedClassAtomStore.saveVerbAtom(verbAtom);
                }
            }
            optedClassAtomStore.saveVerbAtom(verbAtom);
        } catch (UnSupportedTypeException e) {
            e.printStackTrace();
        } finally {
            if(verbAtom != null) {
                optedStoreResources.flushAll();
            }
        }
        return verbAtom;

    }

    @RequestMapping(value = "addInput", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public VerbAtom addInput(@RequestBody SkeletonRequest data){
        VerbAtom verbAtom = null;

        String action = data.getAction();
        String object = data.getObject();
        String label = data.getLabel();

        if(label != null && label.isEmpty()){
            label = object;
        }

        try {

            ClassAtom objectAtom = (ClassAtom) optedClassAtomStore.getOrCreate(object);

            URI verbAtomUri = URI.create(SystemVocabulary.GRAPHITE.toString().concat("/Action#").concat(action));
            verbAtom = new VerbAtom(-1, verbAtomUri, action);

            URI paramUri = URI.create(SystemVocabulary.VERB_ATOM_INPUT_URI.toString()
                    .replace("#","/").concat("#").concat(label));

            final VerbInputAtom verbInputAtom = new VerbInputAtom(paramUri, label, verbAtom);
            optedPropertyAtomStore.saveVerbInputAtom(verbInputAtom);

            verbAtom.addInputAtom(verbInputAtom, objectAtom);
            optedClassAtomStore.saveVerbAtom(verbAtom);

            DomainInput domainInput = new DomainInput(label);
            defaultObjectAtomStore.writeObjectAtom(domainInput);
        } catch (UnSupportedTypeException e) {
            e.printStackTrace();
        } catch (StoreException e) {
            e.printStackTrace();
        } catch (InvalidAtomException e) {
            e.printStackTrace();
        } catch (InvalidStoreException e) {
            e.printStackTrace();
        } catch (ObjectWriteException e) {
            e.printStackTrace();
        } finally {
            defaultStoreResources.flushAll();
            optedStoreResources.flushAll();
        }
        return verbAtom;
    }

    @RequestMapping(value = "addInputAttributes", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public VerbAtom addInputAttributes(@RequestBody SkeletonRequest data){
        VerbAtom verbAtom = null;
        String action = data.getAction();
        String label = data.getLabel();
        String object = data.getObject();

        String userQuery = data.getUserQuery();
        String isOptional = data.getIsOptional();
        try {
            ClassAtom objectAtom = (ClassAtom) optedClassAtomStore.getOrCreate(object);

            URI verbAtomUri = URI.create(SystemVocabulary.GRAPHITE.toString().concat("/Action#").concat(action));
            verbAtom = new VerbAtom(-1, verbAtomUri, action);

            URI paramUri = URI.create(SystemVocabulary.VERB_ATOM_INPUT_URI.toString()
                    .replace("#","/").concat("#").concat(label));

            final VerbInputAtom verbInputAtom = new VerbInputAtom(paramUri, label, verbAtom);

            if(userQuery != null && !userQuery.isEmpty()) {
                verbInputAtom.addAttribute(SystemVocabulary.VERB_ATOM_USER_QUERY_URI.toString(),userQuery);
            }

            verbInputAtom.addAttribute(SystemVocabulary.VERB_ATOM_INPUT_OPTIONAL.toString(),isOptional);

            verbAtom.addInputAtom(verbInputAtom, objectAtom);
            optedClassAtomStore.saveVerbAtom(verbAtom);
        } catch (UnSupportedTypeException e) {
            e.printStackTrace();
        } catch (StoreException e) {
            e.printStackTrace();
        } catch (InvalidAtomException e) {
            e.printStackTrace();
        } finally {
            defaultStoreResources.flushAll();
            optedStoreResources.flushAll();
        }

        return verbAtom;
    }
}
