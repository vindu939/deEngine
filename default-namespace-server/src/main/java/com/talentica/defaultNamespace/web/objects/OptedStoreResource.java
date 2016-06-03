package com.talentica.defaultNamespace.web.objects;

import com.talentica.graphite.store.StoreResources;
import com.talentica.graphite.store.atom.ClassAtomStore;
import com.talentica.graphite.store.atom.ObjectAtomStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by aravindp on 1/6/16.
 */
public class OptedStoreResource {

    @Autowired
    StoreResources optedStoreResources;

    private ClassAtomStore classAtomStore;
    private ObjectAtomStore objectAtomStore;
    //private
    public void setOptedStoreResources(String namespace){
        optedStoreResources = new StoreResources(StoreResources.StoreType.blazegraph, namespace);
    }

    //public ClassAtomStore
}
