package com.kotu.koreatourism.dto.tour;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TourDetailIntroDTO {

        @JsonProperty("contentid")
        private String contentId;

        @JsonProperty("contenttypeid")
        private String contentTypeId;

        @JsonProperty("accomcount")
        private String accomCount;

        @JsonProperty("chkbabycarriage")
        private String chkBabyCarriage;

        @JsonProperty("chkcreditcard")
        private String chkCreditCard;

        @JsonProperty("chkpet")
        private String chkPet;

        @JsonProperty("expagerange")
        private String expAgeRange;

        @JsonProperty("expguide")
        private String expGuide;

        @JsonProperty("heritage1")
        private String heritage1;

        @JsonProperty("heritage2")
        private String heritage2;

        @JsonProperty("heritage3")
        private String heritage3;

        @JsonProperty("infocenter")
        private String infoCenter;

        @JsonProperty("opendate")
        private String openDate;

        @JsonProperty("parking")
        private String parking;

        @JsonProperty("restdate")
        private String restDate;

        @JsonProperty("useseason")
        private String useSeason;

        @JsonProperty("usetime")
        private String useTime;

        @JsonProperty("accomcountculture")
        private String accomCountCulture;

        @JsonProperty("chkbabycarriageculture")
        private String chkBabyCarriageCulture;

        @JsonProperty("chkcreditcardculture")
        private String chkCreditCardCulture;

        @JsonProperty("chkpetculture")
        private String chkPetCulture;

        @JsonProperty("discountinfo")
        private String discountInfo;

        @JsonProperty("infocenterculture")
        private String infoCenterCulture;

        @JsonProperty("parkingculture")
        private String parkingCulture;

        @JsonProperty("parkingfee")
        private String parkingFee;

        @JsonProperty("restdateculture")
        private String restDateCulture;

        @JsonProperty("usefee")
        private String useFee;

        @JsonProperty("usetimeculture")
        private String useTimeCulture;

        @JsonProperty("scale")
        private String scale;

        @JsonProperty("spendtime")
        private String spendTime;

        @JsonProperty("agelimit")
        private String ageLimit;

        @JsonProperty("bookingplace")
        private String bookingPlace;

        @JsonProperty("discountinfofestival")
        private String discountInfoFestival;

        @JsonProperty("eventenddate")
        private String eventEndDate;

        @JsonProperty("eventhomepage")
        private String eventHomePage;

        @JsonProperty("eventplace")
        private String eventPlace;

        @JsonProperty("eventstartdate")
        private String eventStartDate;

        @JsonProperty("festivalgrade")
        private String festivalGrade;

        @JsonProperty("placeinfo")
        private String placeInfo;

        @JsonProperty("playtime")
        private String playTime;

        @JsonProperty("program")
        private String program;

        @JsonProperty("spendtimefestival")
        private String spendTimeFestival;

        @JsonProperty("sponsor1")
        private String sponsor1;

        @JsonProperty("sponsor1tel")
        private String sponsor1Tel;

        @JsonProperty("sponsor2")
        private String sponsor2;

        @JsonProperty("sponsor2tel")
        private String sponsor2Tel;

        @JsonProperty("subevent")
        private String subEvent;

        @JsonProperty("usetimefestival")
        private String useTimeFestival;

        @JsonProperty("distance")
        private String distance;

        @JsonProperty("infocentertourcourse")
        private String infoCenterTourCourse;

        @JsonProperty("schedule")
        private String schedule;

        @JsonProperty("taketime")
        private String takeTime;

        @JsonProperty("theme")
        private String theme;

        @JsonProperty("accomcountleports")
        private String accomCountLeports;

        @JsonProperty("chkbabycarriageleports")
        private String chkBabyCarriageLeports;

        @JsonProperty("chkcreditcardleports")
        private String chkCreditCardLeports;

        @JsonProperty("chkpetleports")
        private String chkPetLeports;

        @JsonProperty("expagerangeleports")
        private String expAgeRangeLeports;

        @JsonProperty("infocenterleports")
        private String infoCenterLeports;

        @JsonProperty("openperiod")
        private String openPeriod;

        @JsonProperty("parkingfeeleports")
        private String parkingFeeLeports;

        @JsonProperty("parkingleports")
        private String parkingLeports;

        @JsonProperty("reservation")
        private String reservation;

        @JsonProperty("restdateleports")
        private String restDateLeports;

        @JsonProperty("scaleleports")
        private String scaleLeports;

        @JsonProperty("usefeeleports")
        private String useFeeLeports;

        @JsonProperty("usetimeleports")
        private String useTimeLeports;

        @JsonProperty("accomcountlodging")
        private String accomCountLodging;

        @JsonProperty("benikia")
        private String benikia;

        @JsonProperty("checkintime")
        private String checkInTime;

        @JsonProperty("checkouttime")
        private String checkOutTime;

        @JsonProperty("chkcooking")
        private String chkCooking;

        @JsonProperty("foodplace")
        private String foodPlace;

        @JsonProperty("goodstay")
        private String goodStay;

        @JsonProperty("hanok")
        private String hanok;

        @JsonProperty("infocenterlodging")
        private String infoCenterLodging;

        @JsonProperty("parkinglodging")
        private String parkingLodging;

        @JsonProperty("pickup")
        private String pickup;

        @JsonProperty("roomcount")
        private String roomCount;

        @JsonProperty("reservationlodging")
        private String reservationLodging;

        @JsonProperty("reservationurl")
        private String reservationUrl;

        @JsonProperty("roomtype")
        private String roomType;

        @JsonProperty("scalelodging")
        private String scaleLodging;

        @JsonProperty("subfacility")
        private String subFacility;

        @JsonProperty("barbecue")
        private String barbecue;

        @JsonProperty("beauty")
        private String beauty;

        @JsonProperty("beverage")
        private String beverage;

        @JsonProperty("bicycle")
        private String bicycle;

        @JsonProperty("campfire")
        private String campfire;

        @JsonProperty("fitness")
        private String fitness;

        @JsonProperty("karaoke")
        private String karaoke;

        @JsonProperty("publicbath")
        private String publicBath;

        @JsonProperty("publicpc")
        private String publicPc;

        @JsonProperty("sauna")
        private String sauna;

        @JsonProperty("seminar")
        private String seminar;

        @JsonProperty("sports")
        private String sports;

        @JsonProperty("refundregulation")
        private String refundRegulation;

        @JsonProperty("chkbabycarriageshopping")
        private String chkBabyCarriageShopping;

        @JsonProperty("chkcreditcard")
        private String chkCreditCardShopping;

        @JsonProperty("shopping")
        private String shopping;

        @JsonProperty("chkpetshopping")
        private String chkPetShopping;

        @JsonProperty("culturecenter")
        private String cultureCenter;

        @JsonProperty("fairday")
        private String fairDay;

        @JsonProperty("infocentershopping")
        private String infoCenterShopping;

        @JsonProperty("opendateshopping")
        private String openDateShopping;

        @JsonProperty("opentime")
        private String openTimeShopping;

        @JsonProperty("parkingshopping")
        private String parkingShopping;

        @JsonProperty("restdateshopping")
        private String restDateShopping;

        @JsonProperty("restroom")
        private String restroom;

        @JsonProperty("saleitem")
        private String saleItem;

        @JsonProperty("saleitemcost")
        private String saleItemCost;

        @JsonProperty("scaleshopping")
        private String scaleShopping;

        @JsonProperty("shopguide")
        private String shopGuide;

        @JsonProperty("chkcreditcardfood")
        private String chkCreditCardFood;

        @JsonProperty("discountinfofood")
        private String discountInfoFood;

        @JsonProperty("firstmenu")
        private String firstMenu;

        @JsonProperty("infocenterfood")
        private String infoCenterFood;

        @JsonProperty("kidsfacility")
        private String kidsFacility;

        @JsonProperty("opendatefood")
        private String openDateFood;

        @JsonProperty("opentimefood")
        private String openTimeFood;

        @JsonProperty("packing")
        private String packing;

        @JsonProperty("parkingfood")
        private String parkingFood;

        @JsonProperty("reservationfood")
        private String reservationFood;

        @JsonProperty("restdatefood")
        private String restDateFood;

        @JsonProperty("scalefood")
        private String scaleFood;

        @JsonProperty("seat")
        private String seat;

        @JsonProperty("smoking")
        private String smoking;

        @JsonProperty("treatmenu")
        private String treatMenu;

        @JsonProperty("lcnsno")
        private String lcnsNo;
}
