package com.talentica.defaultNamespace.web.controllers;

import com.talentica.graphite.store.StoreResources;
import com.talentica.graphite.store.atom.*;
import com.talentica.graphite.store.atom.blazegraph.BlazeGraphObjectAtom;
import com.talentica.graphite.store.atom.system.SystemVocabulary;
import com.talentica.graphite.store.exception.InvalidAtomException;
import com.talentica.graphite.store.exception.StatementStoreException;
import com.talentica.graphite.store.exception.UnSupportedTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by aravindp on 8/6/16.
 */
@RestController
@RequestMapping("/")
public class KnowledgeController {

    // User opted Store Resources
    @Autowired
    private StoreResources optedStoreResources;

    @Autowired
    private ClassAtomStore optedClassAtomStore;
    @Autowired
    private ObjectAtomStore optedObjectAtomStore;
    @Autowired
    private PropertyAtomStore optedPropertyAtomStore;

    @RequestMapping("/createObject")
    public String CreateObjectForClass(@RequestBody Map data){
        String Class = (String) data.get("clazz");
        String value = (String) data.get("value");

        String response = "failed to add value to object";
        try {
            ClassAtom classAtom = (ClassAtom) optedClassAtomStore.getOrCreate(Class);
            ObjectAtom objectAtom = new BlazeGraphObjectAtom(AbstractAtomStore.getUri(value.hashCode()),
                    value, classAtom.getUri());
            objectAtom.addAttribute(SystemVocabulary.GRAPHITE_LABEL.toString(), value);
            optedObjectAtomStore.save(objectAtom);

            response = "Object  [" + value +"] for Class [" + Class + "] created successfully";
        } catch (InvalidAtomException e) {
            e.printStackTrace();

        } catch (UnSupportedTypeException e) {
            e.printStackTrace();
        } catch (StatementStoreException e) {
            e.printStackTrace();
        } finally {
            optedStoreResources.flushAll();
        }

        return response;
    }
}
