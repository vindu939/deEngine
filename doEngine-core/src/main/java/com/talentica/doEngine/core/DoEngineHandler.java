package com.talentica.doEngine.core;

import com.talentica.graphite.search.atom.searcher.doengine.action.AmbiguityResolutionAction;
import com.talentica.graphite.search.context.SystemQuery;
import com.talentica.graphite.search.context.TextContext;
import com.talentica.graphite.search.context.UserQuerySession;
import com.talentica.graphite.search.context.UserResponse;

/**
 * Created by aravindp on 31/5/16.
 */
public class DoEngineHandler {
    private UserQuerySession uqs;

    public DoEngineHandler(UserQuerySession uqs){
        this.uqs  = uqs;
    }

    public void handleTextMessage(String message){
        try {

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
