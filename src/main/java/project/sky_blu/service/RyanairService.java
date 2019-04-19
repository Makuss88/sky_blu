package project.sky_blu.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class RyanairService {

    public String parseURL(String from, String to, String date) {

        StringBuilder st = new StringBuilder();

        String mainDomain = "https://desktopapps.ryanair.com/v4/pl-pl/availability?ADT=1&CHD=0&DateOut=";
        String secondUrl = "&Destination=";
        String thirdUrl = "&FlexDaysOut=4&INF=0&IncludeConnectingFlights=true&Origin=";
        String endUrl = "&RoundTrip=false&TEEN=0&ToUs=AGREED&exists=false";

        st.append(mainDomain);
        st.append(date);
        st.append(secondUrl);
        st.append(to);
        st.append(thirdUrl);
        st.append(from);
        st.append(endUrl);

        return st.toString();

    }

    public String getPrice(String ryanairUrl) {

        List<String> result = new ArrayList<>();
        URL url = null;
        String price = "";

        try {
            url = new URL(ryanairUrl);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String nextLine;
            while ((nextLine = in.readLine()) != null) {
                result.add(nextLine);
            }
            in.close();

            int startPrice = result.get(0).indexOf("amount") + 8; //get +8 bc. "amount:/" chas 8 character to start substring
            int endPrice = result.get(0).indexOf("count") - 4; //get -4, when Admin get good price, (sht price has 11,9900 and example 1234,9900 - we make when this substring ends)

            if(endPrice<startPrice){
                return "BŁĄD CIECIU!";
            }

            price = result.get(0).substring(startPrice, endPrice);

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return price;
    }
}
