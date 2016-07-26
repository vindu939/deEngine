package com.talentica.defaultNamespace;

import com.talentica.defaultNamespace.cubeData.User;
import com.talentica.graphite.store.StoreResources;
import com.talentica.graphite.store.api.annotation.GraphiteAnnotationParser;
import com.talentica.graphite.store.atom.AtomType;
import com.talentica.graphite.store.atom.ClassAtomStore;
import com.talentica.graphite.store.atom.ObjectAtomStore;
import com.talentica.graphite.store.atom.PropertyAtomStore;
import com.talentica.graphite.store.exception.InvalidStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aravindp on 23/5/16.
 */
@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Value("${default.namespace}")
    private String defaultNamespace;

    @Value("${opted.namespace}")
    private String userOptedNamespace;

    @Bean
    public StoreResources defaultStoreResources(){
        return new StoreResources(StoreResources.StoreType.blazegraph, defaultNamespace);
    }

    @Bean
    public StoreResources optedStoreResources(){
        return new StoreResources(StoreResources.StoreType.blazegraph, userOptedNamespace);
    }

    @Bean
    public ClassAtomStore defaultClassAtomStore(StoreResources defaultStoreResources) throws InvalidStoreException {
        return (ClassAtomStore) defaultStoreResources.getAtomStore(AtomType.domain_class);
    }

    @Bean
    public ObjectAtomStore defaultObjectAtomStore(StoreResources defaultStoreResources) throws InvalidStoreException {
        return (ObjectAtomStore) defaultStoreResources.getAtomStore(AtomType.domain_obj);
    }

    @Bean
    public PropertyAtomStore defaultPropertyAtomStore(StoreResources defaultStoreResources) throws InvalidStoreException {
        return (PropertyAtomStore) defaultStoreResources.getAtomStore(AtomType.domain_prop);
    }

    @Bean
    public ClassAtomStore optedClassAtomStore(StoreResources optedStoreResources) throws InvalidStoreException {
        return (ClassAtomStore) optedStoreResources.getAtomStore(AtomType.domain_class);
    }

    @Bean
    public ObjectAtomStore optedObjectAtomStore(StoreResources optedStoreResources) throws InvalidStoreException {
        return (ObjectAtomStore) optedStoreResources.getAtomStore(AtomType.domain_obj);
    }

    @Bean
    public PropertyAtomStore optedPropertyAtomStore(StoreResources optedStoreResources) throws InvalidStoreException {
        return (PropertyAtomStore) optedStoreResources.getAtomStore(AtomType.domain_prop);
    }

    @Autowired
    StoreResources defaultStoreResources;

    @Override
    public void run(String... strings) throws Exception {
        GraphiteAnnotationParser gap = new GraphiteAnnotationParser(defaultStoreResources);
        final String[] packages = {"com.talentica.defaultNamespace.knowledge"};
        gap.parse(packages);

        GraphiteAnnotationParser gap1 = new GraphiteAnnotationParser(optedStoreResources());
        final String[] packages1 = {"com.talentica.defaultNamespace.cubeData"};
        gap1.parse(packages1);

        final ObjectAtomStore objectAtomStore = optedObjectAtomStore(optedStoreResources());
        User user = new User("Aravind Pilla");
        user.setUuid("12345");
        objectAtomStore.writeObjectAtom(user);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
