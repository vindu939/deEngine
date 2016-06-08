package com.talentica.doEngine.core;

import com.talentica.doEngine.core.Responses.GoodByes;
import com.talentica.doEngine.core.Responses.Greetings;
import com.talentica.graphite.search.atom.searcher.doengine.action.AmbiguityResolutionAction;
import com.talentica.graphite.search.context.SystemQuery;
import com.talentica.graphite.search.context.TextContext;
import com.talentica.graphite.search.context.UserQuerySession;

/**
 * Created by aravindp on 31/5/16.
 */
public class DoEngineHandler {
    private UserQuerySession uqs;

    public DoEngineHandler(UserQuerySession uqs){
        this.uqs  = uqs;
    }

    public void handleTextMessage(String message) throws Exception{
        uqs.start(message);

        while (true) {
            // Get next suitable action
            AmbiguityResolutionAction nextAction = uqs.getNextAction();
            if (null == nextAction) {
                break;
            }

            if (nextAction.execute()) {
                // Break if the action produced a query
                TextContext context = uqs.getContext();
                System.out.println(context.getActor() + " : "
                        +context.getType() + " : " + context.getValue());
                if (context instanceof SystemQuery) {
                    break;
                }
            }

        }
    }
}
