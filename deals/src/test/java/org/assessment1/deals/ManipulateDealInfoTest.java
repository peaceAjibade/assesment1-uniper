package org.assessment1.deals;
import org.assessment1.deals.*;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManipulateDealInfoTest {
    @Test
    public void testConvertVolume(){
        DealInfo deal1 = new DealInfo(8088761, "ECC", "TTF", 100, "KWh", "25-11-21");
        DealInfo deal2 = new DealInfo(8088762, "GTS", "TTF", 150, "MWh", "26-11-21");
        DealInfo deal3 = new DealInfo(8088763, "ECC", "TTF", 100, "MWh", "25-11-21");

        List<DealInfo> testVals = new ArrayList<>();
        testVals.add(deal1);
        testVals.add(deal2);
        testVals.add(deal3);

        List<DealInfo> deals = new ManipulateDealInfo().convertVolume(testVals);
        deal1.setVolume(deal1.getVolume()*1000);
        Assert.assertEquals(testVals, deals);
    }
    //@Test
//    public void testReadDealInfo(){
//        DealInfo deal1 = new DealInfo(8088761, "ECC", "TTF", 100, "KWh", "25-11-21");
//        DealInfo deal2 = new DealInfo(8088762, "GTS", "TTF", 150, "MWh", "26-11-21");
//        DealInfo deal3 = new DealInfo(8088763, "ECC", "TTF", 100, "MWh", "25-11-21");
//
//        List<DealInfo> testVals = new ArrayList<>();
//        testVals.add(deal1);
//        testVals.add(deal2);
//        testVals.add(deal3);
//
//        List<DealInfo> deals = new ManipulateDealInfo().readDealInfo("deal_info1.csv");
//        Assert.assertEquals(testVals, deals);
//    }

    @Test
    public void testReadDealInfoNull(){
        List<DealInfo> deals = new ManipulateDealInfo().readDealInfo("null_test.csv");
        Assert.assertEquals(null, deals);

    }

    @Test
    public void testAggregateDeals(){
        DealInfo deal1 = new DealInfo(8088761, "ECC", "TTF", 100, "KWh", "25-11-21");
        DealInfo deal2 = new DealInfo(8088762, "GTS", "TTF", 150, "MWh", "26-11-21");
        DealInfo deal3 = new DealInfo(8088763, "ECC", "TTF", 100, "MWh", "25-11-21");

        List<DealInfo> testVals = new ArrayList<>();
        testVals.add(deal1);
        testVals.add(deal2);
        testVals.add(deal3);

        List<DealInfo> testValsECC = new ArrayList<>();
        List<DealInfo> testValsGTS = new ArrayList<>();
        testValsECC.add(deal1);
        testValsGTS.add(deal2);
        testValsECC.add(deal3);

        Map<String, List<DealInfo>> aggregatedDealList = new HashMap<>();
        aggregatedDealList.put("ECC", testValsECC);
        aggregatedDealList.put("GTS", testValsGTS);

        Map<String, List<DealInfo>> deals = new ManipulateDealInfo().aggregateDeals(testVals);
        Assert.assertEquals(aggregatedDealList, deals);
    }

}
