package project.sky_blu.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sky_blu.domain.City;
import project.sky_blu.persistance.CityRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class RyanairService {

    @Autowired
    private CityRepository cityRepository;

    private List<String> dates;
    private List<String> prices;

    public String parseURL(String from, String to, String date) {

        List<City> citiesFrom = cityRepository.findAllByOfficialName(from);
        List<City> citiesTo = cityRepository.findAllByOfficialName(to);

        String cityFrom = citiesFrom.get(0).getFlyName();
        String cityTo = citiesTo.get(0).getFlyName();

        StringBuilder st = new StringBuilder();

        String mainDomain = "https://desktopapps.ryanair.com/v4/pl-pl/availability?ADT=1&CHD=0&DateOut=";
        String secondUrl = "&Destination=";
        String thirdUrl = "&FlexDaysOut=4&INF=0&IncludeConnectingFlights=true&Origin=";
        String endUrl = "&RoundTrip=false&TEEN=0&ToUs=AGREED&exists=false";

        st.append(mainDomain);
        st.append(date);
        st.append(secondUrl);
        st.append(cityTo);
        st.append(thirdUrl);
        st.append(cityFrom);
        st.append(endUrl);

        return st.toString();

    }

    public void getPriceAndData(String ryanairUrl) {

        dates = new ArrayList<>();
        prices = new ArrayList<>();

        List<String> result = new ArrayList<>();
        URL url = null;
        try {
            url = new URL(ryanairUrl);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String nextLine;
            while ((nextLine = in.readLine()) != null) {
                result.add(nextLine);
            }
            in.close();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        while (true) {

            if (!result.get(0).contains("fareClass")) {
                break;
            }

            int count_to_start = result.get(0).indexOf("fareClass");
            result.add(0, result.get(0).substring(count_to_start+1));

            int startPrice = result.get(0).indexOf("amount") + 8; //get +8 bc. "amount:/" chas 8 character to start substring
            int endPrice = result.get(0).indexOf("count") - 4; //get -4, when Admin get good price, (sht price has 11,9900 and example 1234,9900 - we make when this substring ends)
            String price = result.get(0).substring(startPrice,endPrice);

            int substring_date = result.get(0).indexOf("time") + 8; //get +8 to start substring
            String date = result.get(0).substring(substring_date, substring_date + 10);


            prices.add(price);
            dates.add(date);

        }
    }
}
