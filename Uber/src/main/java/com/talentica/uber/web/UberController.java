package com.talentica.uber.web;

import com.talentica.uber.Utils;
import com.uber.sdk.rides.client.model.PriceEstimate;
import com.uber.sdk.rides.client.model.PriceEstimatesResponse;
import com.uber.sdk.rides.client.model.TimeEstimate;
import com.uber.sdk.rides.client.model.TimeEstimatesResponse;
import com.uber.sdk.rides.client.services.RidesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by aravindp on 15/6/16.
 */
@RestController
@RequestMapping("/")
public class UberController {

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${redirectUrl}")
    private String redirectUrl;


    @RequestMapping(value = "fareEstimate", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String fareEstimate(@RequestHeader("Authorization") String auth, @RequestBody Map data) throws IOException {

        String userId = auth.split(" ")[1];
        float start_latitude = Float.valueOf((String) data.get("start_latitude"));
        float start_longitude = Float.valueOf((String) data.get("start_longitude"));
        float end_latitude = Float.valueOf((String) data.get("end_latitude"));
        float end_longitude = Float.valueOf((String) data.get("end_longitude"));
        String start_address = (String) data.get("start_address");
        String end_address = (String) data.get("end_address");

        RidesService service = Utils.getService(userId);

        Response<PriceEstimatesResponse> response = service.getPriceEstimates(start_latitude, start_longitude, end_latitude, end_longitude).execute();

        List<PriceEstimate> priceEstimates = response.body().getPrices();

        StringBuffer price = new StringBuffer();
        priceEstimates.stream()
                .forEach(x -> {
                    String displayName = x.getDisplayName();
                    int lowEstimate = x.getLowEstimate();
                    int highEstimate = x.getHighEstimate();
                    String currencyCode = x.getCurrencyCode();

                     price.append("\n"+ displayName + " : " + lowEstimate + "-" + highEstimate +
                            " " + currencyCode);

                });

        String finalMessage = "Estimates from "+ start_address + " to " + end_address + " is";
        String distance = "Total distance : " + priceEstimates.get(0).getDistance() + " miles";
        String time = "Total time : " + priceEstimates.get(0).getDuration()/60F + " min";

        finalMessage = finalMessage + "\n" + distance + "\n" + time + price;
        return finalMessage;
    }

    @RequestMapping(value = "timeEstimate", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String timeEstimate(@RequestHeader("Authorization") String userId, @RequestBody Map data) throws IOException {
        float start_latitude = Float.valueOf((String) data.get("start_latitude"));
        float start_longitude = Float.valueOf((String) data.get("start_longitude"));
        String start_address = (String) data.get("start_address");

        RidesService service = Utils.getService(userId);

        Response<TimeEstimatesResponse> response = service.getPickupTimeEstimate(start_latitude, start_longitude, "").execute();

        List<TimeEstimate> timeEstimates = response.body().getTimes();

        StringBuffer time = new StringBuffer("Estimated time of pickup for " + start_address);

        timeEstimates.stream()
                .forEach(x -> time.append("\n" + x.getDisplayName() + " : " + x.getEstimate()/60F + " min"));

        return time.toString();
    }

}
