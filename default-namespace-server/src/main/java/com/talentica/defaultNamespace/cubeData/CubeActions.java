package com.talentica.defaultNamespace.cubeData;

import com.talentica.graphite.store.api.annotation.ClassNode;
import com.talentica.graphite.store.api.annotation.VerbInput;
import com.talentica.graphite.store.api.annotation.VerbNode;

/**
 * Created by aravindp on 18/7/16.
 */
@ClassNode
public class CubeActions {
    @VerbNode(restEndpoint = "/user/reminders", synonym = Synonyms.SHOW + Synonyms.DUES + Synonyms.LATE + Synonyms.PAID, endpointType = "GET")
    public void Reminders(){

    }

    @VerbNode(restEndpoint = "/dream", synonym = Synonyms.DREAMS, endpointType = "GET")
    public void Dreams(){

    }

    @VerbNode(restEndpoint = "/dream", synonym = Synonyms.WISHLIST, endpointType = "GET")
    public void Wishlist(){

    }

    @VerbNode(restEndpoint = "/user/accounts", synonym = Synonyms.BALANCE, endpointType = "GET")
    public void Balance(){

    }

    @VerbNode(restEndpoint = "/user/pay", synonym = Synonyms.PAY, endpointType = "GET")
    public void Pay(@VerbInput(userQuery = "To whom?", label = "user") User user,
                    @VerbInput(userQuery = "how much?", label = "amount") Number amount){

    }

   /* @VerbNode(restEndpoint = "deeplink", synonym = Synonyms.PAY)
    public void payBill(@VerbInput(userQuery = "operator name?", isOptional = true) ServiceOperator serviceOperator,
                    @VerbInput(userQuery = "operator type?", isOptional = true) ServiceType serviceType,
                    @VerbInput(userQuery = "reminder type?") ReminderType reminderType){

    }*/

    @VerbNode(restEndpoint = "deeplink", synonym = Synonyms.ADD, endpointType = "GET")
    public void add(){

    }
}
