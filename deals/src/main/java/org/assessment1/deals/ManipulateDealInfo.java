package org.assessment1.deals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;

public class ManipulateDealInfo {
    public static void main(String[] args) {
        List<DealInfo> deals = readDealInfo("deal_info.csv");
        convertVolume(deals);
        Map<String, List<DealInfo>> aggregatedDeals = aggregateDeals(deals);
        printBasedOnDate(aggregatedDeals);

    }


    public static List<DealInfo> readDealInfo(String src){
        List<DealInfo> dealInfoList = new ArrayList<>();

        try{
            //initialise input stream
            InputStream inputStream = ManipulateDealInfo.class.getResourceAsStream(src);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //read file line by line
            String line;
            while((line = reader.readLine())!=null){
                String[] currentLine = line.split(",");
                //first check if first line is a header
                //if not create DealInfo objects to add to list
                if(!(currentLine[0].equals("deal_num"))){
                    int dealNum = Integer.parseInt(currentLine[0]);
                    String cp = currentLine[1];
                    String loc = currentLine[2];
                    int vol = Integer.parseInt(currentLine[3]);
                    String unit = currentLine[4];
                    String date = currentLine[5];

                    DealInfo deal = new DealInfo(dealNum, cp,loc, vol, unit, date);
                    dealInfoList.add(deal);
                }
            }
            inputStream.close();
        }


        catch(IOException e){
            return null;
        }
        catch(NullPointerException e){
            return null;
        }


        return dealInfoList;
    }

    public static List<DealInfo> convertVolume(List<DealInfo> deals) {
        //filter those deals whose volume is in KWh and convert them to MWh
        deals.stream()
                .filter(deal -> !(deal.getVolumeUnit().equals("MWh")))
                .forEach(deal -> {
                    deal.setVolume(deal.getVolume()*1000);
                    deal.setVolumeUnit("MWh");
                });

        return deals;
    }

    public static Map<String, List<DealInfo>> aggregateDeals(List<DealInfo> deals) {
        //find deals with same counterparty and location
        //hardcode for now as all locations are the same and only 2 counterparty types
        //collect deals with counterparty ECC
        List<DealInfo> dealECC = deals.stream()
                .filter(deal -> deal.getCounterparty().equals("ECC"))
                .collect(Collectors.toList());
        //collect deals with counterparty GTS
        List<DealInfo> dealGTS = deals.stream()
                .filter(deal -> deal.getCounterparty().equals("GTS"))
                .collect(Collectors.toList());

        Map<String, List<DealInfo>> aggregatedDealList = new HashMap<>();
        aggregatedDealList.put("ECC", dealECC);
        aggregatedDealList.put("GTS", dealGTS);

        return aggregatedDealList;
    }

    public static void printBasedOnDate(Map<String, List<DealInfo>> deals){
        //print based on date
        //check list from each counterparty if they have the same date, sum the volumes and print out
        List<DealInfo> listECC = deals.get("ECC");
        List<DealInfo> listGTS = deals.get("GTS");

        List<DealInfo> sumListECC = listECC;
        List<DealInfo> sumListGTS = listGTS;


        for(int i = 0; i < sumListECC.size(); i++){
            DealInfo deal = sumListECC.get(i);
            String currentDate = deal.getDeliveryDate();
            for(int j = i+1; j < sumListECC.size(); j++){
                DealInfo deal2 = sumListECC.get(j);
                String date = deal2.getDeliveryDate();
                if(date.equals(currentDate)){
                    int sum = deal.getVolume() + deal2.getVolume();
                    deal.setVolume(sum);
                    sumListECC.remove(j);
                    j--;
                }
            }
            System.out.println(deal);
        }

        for(int i = 0; i < sumListGTS.size(); i++){
            DealInfo deal = sumListGTS.get(i);
            String currentDate = deal.getDeliveryDate();
            for(int j = i+1; j < sumListGTS.size(); j++){
                DealInfo deal2 = sumListGTS.get(j);
                String date = deal2.getDeliveryDate();
                if(date.equals(currentDate)){
                    int sum = deal.getVolume() + deal2.getVolume();
                    deal.setVolume(sum);
                    sumListGTS.remove(j);
                    j--;
                }
            }
            System.out.println(deal);
        }


    }

}
