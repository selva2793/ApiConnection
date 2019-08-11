package com.androidselva.apiconnection.UTILS;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.TimeZone;

@SuppressLint("SimpleDateFormat")
public class DateFormat {
    public static Hashtable<String, String> timeZoneShortNameHash = getTimeZoneShortNameHash();

    public static String getDateStr(Date date){
        String dateStr = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            sf.setTimeZone(TimeZone.getTimeZone("UTC"));
            dateStr = sf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }
    public static Date getDate(String date){
        Date dateStr = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateStr = sf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static String getStrDateMilli(long Timemillis){
        String dateStr = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Timemillis);

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            dateStr = sf.format(cal.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }

    public static Date getDateMilli(long Timemillis){
        Calendar cal = null;
        try {
            cal = Calendar.getInstance();
            cal.setTimeInMillis(Timemillis);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cal.getTime();
    }

    public static Date getDate(String strDate, String timeZone){
        Date dateStr = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			/*if(timeZone != null){
				sf.setTimeZone(TimeZone.getTimeZone("UTC"));
			}*/
            TimeZone timeZoneTZ = TimeZone.getTimeZone("UTC");
            sf.setTimeZone(timeZoneTZ);
            if(strDate != null){
                dateStr = sf.parse(strDate);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    public static String getTimezone(){
        String timString = TimeZone.getDefault().getID();
        return timString;
    }
    public static String formatIntHHMM(int delaySec) {
        if(delaySec > 0){
            String DelayTime = null;
            try {
                String hour = Integer.toString((delaySec / 60 ));
                String minute = Integer.toString((delaySec % 60));

                for(int i2 = 0 ; i2 < 2 ; i2++){

                    if (minute.length() < 2) {
                        minute = "0" + minute;
                    }
                    if (hour.length() < 2) {
                        hour = "0" + hour;
                    }
                }
                DelayTime = hour + ":" +  minute;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return DelayTime;
        }
        else{
            return "00:00";
        }
    }
    public static String getDateStrMMDDYYYY(Date date, String timeZone){
        String dateStr = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
            sf.setTimeZone(TimeZone.getTimeZone(timeZone));
            dateStr = sf.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }

    public static String getDateStrDDMMYYY(Date date, String timeZone){
        String dateStr = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
            sf.setTimeZone(TimeZone.getTimeZone(timeZone));
            dateStr = sf.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }


    public static String getDateStrYYYYMMDD(Date date, String timeZone){
        String dateStr = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            sf.setTimeZone(TimeZone.getTimeZone(timeZone));
            dateStr = sf.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    public static String getDateStrDisplay(Date date){
        String dateStr = null;
        try {
            Calendar calendarInput = Calendar.getInstance();
            calendarInput.setTime(date);

            Calendar today = Calendar.getInstance();

            SimpleDateFormat sfDateTime = new SimpleDateFormat("d MMM, hh:mma");
            SimpleDateFormat sfTime = new SimpleDateFormat("hh:mma");
            dateStr = "";
            if(date != null){
                if (calendarInput.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                        calendarInput.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
                    dateStr = sfTime.format(date);
                }else{
                    dateStr = sfDateTime.format(date);
                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    public static String getDateStrDisplayDate(Date date){
        String dateStr = null;
        try {
            Calendar calendarInput = Calendar.getInstance();
            calendarInput.setTime(date);

            Calendar today = Calendar.getInstance();

            SimpleDateFormat sfDateTime = new SimpleDateFormat("d MMM, hh:mma");
            SimpleDateFormat sfTime = new SimpleDateFormat("hh:mma");
            dateStr = "";
            if(date != null){
                if (calendarInput.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                        calendarInput.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
                    dateStr = sfTime.format(date);
                }else{
                    dateStr = sfDateTime.format(date);
                }
            }
            String shortName = getTimeZone_OffSet_ShortName();

            if(dateStr != null && shortName != null){
                dateStr = dateStr + " " + shortName;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    public static Date getDate_Timezone(Date inputDate, String timezone){
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        //Local time zone
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        if(timezone != null){
            dateFormatLocal.setTimeZone(TimeZone.getTimeZone(timezone));
        }
        //Time in GMT
        Date myDate = null;
        try {
            myDate = dateFormatLocal.parse( dateFormatGmt.format(inputDate) );
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return myDate;
    }
    @SuppressWarnings("deprecation")
    public static Date getDateDMMMHHMM(String date){
        SimpleDateFormat sf = new SimpleDateFormat("d MMM, hh:mm a");
        Date dateStr = null;
        try {
            dateStr = sf.parse(date);
            if(dateStr != null){
                dateStr.setYear(new Date().getYear());
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    public static String getWeekBeforeDate(){
        String startDate = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -7);
            calendar.set(Calendar.HOUR_OF_DAY, 00);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND,00);
            startDate = DateFormat.getDateStr(calendar.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return startDate;
    }

    public static String getFormatedDate(String inputDateString) {
        String receivedTime = CovertUTCStringDateToDefaultString(inputDateString);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formatedDate = null;
        try {
            Date dateStr = null;
            if(receivedTime != null){
                dateStr = sf.parse(receivedTime);
            }
            Calendar c1 = Calendar.getInstance(); // today
            //c1.add(Calendar.DAY_OF_YEAR, -1); // yesterday

            Calendar c2 = Calendar.getInstance();
            c2.setTime(dateStr); // your date

            if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                    && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                    && c1.get(Calendar.DATE) == c2.get(Calendar.DATE)) {
                SimpleDateFormat sf1 = new SimpleDateFormat("hh:mma"); //hh:mm a
                formatedDate = sf1.format(c2.getTime());


            }else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                    && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
                SimpleDateFormat sf1 = new SimpleDateFormat("d MMM,hh:mma"); //hh:mm a
                formatedDate = sf1.format(c2.getTime());
            }else{
                SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd"); //hh:mm a
                formatedDate = sf1.format(c2.getTime());
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return formatedDate;
    }
    public static String getCurrentTimezoneOffset() {
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
        offset = (offsetInMillis >= 0 ? "+" : "-") + offset;
        return offset;
    }
    public static String getTimeZone_OffSet_ShortName(){
        String timeZoneOffsetName = TimeZone.getDefault().getID();;
        TimeZone str=  TimeZone.getTimeZone(timeZoneOffsetName);
        String shortName = str.getDisplayName(str.useDaylightTime(), TimeZone.SHORT);
        if(shortName.length() > 3){
            String longName = getTimeZone_OffSet_LONGName();
            if(timeZoneShortNameHash.containsKey(longName)){
                shortName = timeZoneShortNameHash.get(longName);
            }
        }
        return shortName;
    }
    public static String getTimeZone_OffSet_LONGName(){
        String timeZoneOffsetName = TimeZone.getDefault().getID();;
        TimeZone str=  TimeZone.getTimeZone(timeZoneOffsetName);
        return str.getDisplayName(str.useDaylightTime(), TimeZone.LONG);
    }
    public static String getCurrentUTCDate(){
        String currentDate = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            sf.setTimeZone(TimeZone.getTimeZone("UTC"));
            currentDate = sf.format(new Date());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentDate;
    }
    public static Date CurrentUTC_Date(){
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        //Local time zone
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //Time in GMT
        Date myDate = null;
        try {
            myDate = dateFormatLocal.parse( dateFormatGmt.format(new Date()) );
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return myDate;
    }
    public static Hashtable<String, String> getTimeZoneShortNameHash(){//http://www.timeanddate.com/library/abbreviations/timezones/
        Hashtable<String, String> timezoneList = new Hashtable<String, String>();
        timezoneList.put("Alpha Time Zone", "A");
        timezoneList.put("Australian Central Daylight Time", "ACDT");
        timezoneList.put("Australian Central Standard Time", "ACST");
        timezoneList.put("Atlantic Daylight Time", "ADT");
        timezoneList.put("Australian Eastern Daylight Time", "AEDT");
        timezoneList.put("Australian Eastern Standard Time", "AEST");
        timezoneList.put("Afghanistan Time", "AFT");
        timezoneList.put("Alaska Daylight Time", "AKDT");
        timezoneList.put("Alaska Standard Time", "AKST");
        timezoneList.put("Alma-Ata Time", "ALMT");
        timezoneList.put("Armenia Summer Time", "AMST");
        timezoneList.put("Amazon Summer Time", "AMST");
        timezoneList.put("Armenia Time", "AMT");
        timezoneList.put("Amazon Time", "AMT");
        timezoneList.put("Anadyr Summer Time", "ANAST");
        timezoneList.put("Anadyr Time", "ANAT");
        timezoneList.put("Aqtobe Time", "AQTT");
        timezoneList.put("Argentina Time", "ART");
        timezoneList.put("Arabia Standard Time", "AST");
        timezoneList.put("Atlantic Standard Time", "AST");
        timezoneList.put("Australian Western Daylight Time", "AWDT");
        timezoneList.put("Australian Western Standard Time", "AWST");
        timezoneList.put("Azores Summer Time", "AZOST");
        timezoneList.put("Azores Time", "AZOT");
        timezoneList.put("Azerbaijan Summer Time", "AZST");
        timezoneList.put("Azerbaijan Time", "AZT");
        //B
        timezoneList.put("Bravo Time Zone", "B");
        timezoneList.put("Brunei Darussalam Time", "BNT");
        timezoneList.put("Bolivia Time", "BOT");
        timezoneList.put("Brasilia Summer Time", "BRST");
        timezoneList.put("Bras�lia time", "BRT");
        timezoneList.put("Bangladesh Standard Time", "BST");
        timezoneList.put("British Summer Time", "BST");
        timezoneList.put("Bhutan Time", "BTT");
        //C
        timezoneList.put("Charlie Time Zone", "C");
        timezoneList.put("Casey Time", "CAST");
        timezoneList.put("Central Africa Time", "CAT");
        timezoneList.put("Cocos Islands Time", "CCT");
        timezoneList.put("Cuba Daylight Time", "CDT");
        timezoneList.put("Central Daylight Time", "CDT");
        timezoneList.put("Central European Summer Time", "CEST");
        timezoneList.put("Central European Time", "CET");
        timezoneList.put("Chatham Island Daylight Time", "CHADT");
        timezoneList.put("Chatham Island Standard Time", "CHAST");
        timezoneList.put("Cook Island Time", "CKT");
        timezoneList.put("Chile Summer Time", "CLST");
        timezoneList.put("Chile Standard Time", "CLT");
        timezoneList.put("Colombia Time", "COT");
        timezoneList.put("China Standard Time", "CST");
        timezoneList.put("Central Standard Time", "CST");
        timezoneList.put("Cuba Standard Time", "CST");
        timezoneList.put("Cape Verde Time", "CVT");
        timezoneList.put("Christmas Island Time", "CXT");
        timezoneList.put("Chamorro Standard Time", "ChST");
        //D
        timezoneList.put("Delta Time Zone", "D");
        timezoneList.put("Davis Time", "DAVT");
        //E
        timezoneList.put("Echo Time Zone", "E");
        timezoneList.put("Easter Island Summer Time", "EASST");
        timezoneList.put("Easter Island Standard Time", "EAST");
        timezoneList.put("Eastern Africa Time", "EAT");
        timezoneList.put("East Africa Time", "EAT");
        timezoneList.put("Ecuador Time", "ECT");
        timezoneList.put("Eastern Daylight Time", "EDT");
        timezoneList.put("Eastern European Summer Time", "EEST");
        timezoneList.put("Eastern European Time", "EET");
        timezoneList.put("Eastern Greenland Summer Time", "EGST");
        timezoneList.put("East Greenland Time", "EGT");
        timezoneList.put("Eastern Standard Time", "EST");
        timezoneList.put("Tiempo del Este", "ET");
        //F
        timezoneList.put("Foxtrot Time Zone", "F");
        timezoneList.put("Fiji Summer Time", "FJST");
        timezoneList.put("Fiji Time", "FJT");
        timezoneList.put("Falkland Islands Summer Time", "FKST");
        timezoneList.put("Falkland Island Time", "FKT");
        timezoneList.put("Fernando de Noronha Time", "FNT");
        //G
        timezoneList.put("Golf Time Zone", "G");
        timezoneList.put("Galapagos Time", "GALT");
        timezoneList.put("Gambier Time", "GAMT");
        timezoneList.put("Georgia Standard Time", "GET");
        timezoneList.put("French Guiana Time", "GFT");
        timezoneList.put("Gilbert Island Time", "GILT");
        timezoneList.put("Greenwich Mean Time", "GMT");
        timezoneList.put("Gulf Standard Time", "GST");
        timezoneList.put("Guyana Time", "GYT");
        //H
        timezoneList.put("Hotel Time Zone", "H");
        timezoneList.put("Heure Avanc�e de l'Atlantique", "HAA");
        timezoneList.put("Heure Avanc�e du Centre", "HAC");
        timezoneList.put("Hawaii-Aleutian Daylight Time", "HADT");
        timezoneList.put("Heure Avanc�e de l'Est", "HAE");
        timezoneList.put("Heure Avanc�e du Pacifique", "HAP");
        timezoneList.put("Heure Avanc�e des Rocheuses", "HAR");
        timezoneList.put("Hawaii-Aleutian Standard Time", "HAST");
        timezoneList.put("Heure Avanc�e de Terre-Neuve", "HAT");
        timezoneList.put("Heure Avanc�e du Yukon", "HAY");
        timezoneList.put("Hong Kong Time", "HKT");
        timezoneList.put("Hora Legal de Venezuela", "HLV");
        timezoneList.put("Heure Normale de l'Atlantique", "HNA");
        timezoneList.put("Heure Normale du Centre", "HNC");
        timezoneList.put("Heure Normale de l'Est", "HNE");
        timezoneList.put("Heure Normale du Pacifique", "HNP");
        timezoneList.put("Heure Normale des Rocheuses", "HNR");
        timezoneList.put("Heure Normale de Terre-Neuve", "HNT");
        timezoneList.put("Heure Normale du Yukon", "HNY");
        timezoneList.put("Hovd Time", "HOVT");
        timezoneList.put("Zulu Time Zone", "Z");
        timezoneList.put("Yekaterinburg Time", "YEKT");
        timezoneList.put("Yekaterinburg Summer Time", "YEKST");
        timezoneList.put("Yap Time", "YAPT");
        timezoneList.put("Yakutsk Time", "YAKT");
        timezoneList.put("Yakutsk Summer Time", "YAKST");
        timezoneList.put("Yankee Time Zone", "Y");
        timezoneList.put("X-ray Time Zone", "X");
        timezoneList.put("Western Sahara Standard Time", "WT");
        timezoneList.put("West Samoa Time", "WST");
        timezoneList.put("Western Sahara Summer Time", "WST");
        timezoneList.put("Central Indonesian Time", "WITA");
        timezoneList.put("Eastern Indonesian Time", "WIT");
        timezoneList.put("Western Indonesian Time", "WIB");
        timezoneList.put("West Greenland Time", "WGT");
        timezoneList.put("Western Greenland Summer Time", "WGST");
        timezoneList.put("Wallis and Futuna Time", "WFT");
        timezoneList.put("Westeurop�ische Zeit", "WEZ");
        timezoneList.put("Western European Time", "WET");
        timezoneList.put("Westeurop�ische Sommerzeit", "WESZ");
        timezoneList.put("Western European Summer Time", "WEST");
        timezoneList.put("West Africa Time", "WAT");
        timezoneList.put("West Africa Summer Time", "WAST");
        timezoneList.put("Whiskey Time Zone", "W");
        timezoneList.put("Vanuatu Time", "VUT");
        timezoneList.put("Vladivostok Time", "VLAT");
        timezoneList.put("Venezuelan Standard Time", "VET");
        timezoneList.put("Victor Time Zone", "V");
        timezoneList.put("Uzbekistan Time", "UZT");
        timezoneList.put("Uruguay Time", "UYT");
        timezoneList.put("Uruguay Summer Time", "UYST");
        timezoneList.put("Universal Time Coordinated", "UTC");
        timezoneList.put("Ulaanbaatar Time", "ULAT");
        timezoneList.put("Uniform Time Zone", "U");
        timezoneList.put("Tuvalu Time", "TVT");
        timezoneList.put("Turkmenistan Time", "TMT");
        timezoneList.put("East Timor Time", "TLT");
        timezoneList.put("Tokelau Time", "TKT");
        timezoneList.put("Tajikistan Time", "TJT");
        timezoneList.put("French Southern and Antarctic Time", "TFT");
        timezoneList.put("Tahiti Time", "TAHT");
        timezoneList.put("Tango Time Zone", "T");
        timezoneList.put("Samoa Standard Time", "SST");
        timezoneList.put("Suriname Time", "SRT");
        timezoneList.put("Singapore Time", "SGT");
        timezoneList.put("Seychelles Time", "SCT");
        timezoneList.put("Solomon IslandsTime", "SBT");
        timezoneList.put("South Africa Standard Time", "SAST");
        timezoneList.put("Samara Time", "SAMT");
        timezoneList.put("Sierra Time Zone", "S");
        timezoneList.put("Reunion Time", "RET");
        timezoneList.put("Romeo Time Zone", "R");
        timezoneList.put("Quebec Time Zone", "Q");
        timezoneList.put("Paraguay Time", "PYT");
        timezoneList.put("Paraguay Summer Time", "PYST");
        timezoneList.put("Palau Time", "PWT");
        timezoneList.put("Tiempo del Pac�fico", "PT");
        timezoneList.put("Pitcairn Standard Time", "PST");
        timezoneList.put("Pacific Standard Time", "PST");
        timezoneList.put("Pohnpei Standard Time", "PONT");
        timezoneList.put("Pierre & Miquelon Standard Time", "PMST");
        timezoneList.put("Pierre & Miquelon Daylight Time", "PMDT");
        timezoneList.put("Pakistan Standard Time", "PKT");
        timezoneList.put("Philippine Time", "PHT");
        timezoneList.put("Phoenix Island Time", "PHOT");
        timezoneList.put("Papua New Guinea Time", "PGT");
        timezoneList.put("Kamchatka Time", "PETT");
        timezoneList.put("Kamchatka Summer Time", "PETST");
        timezoneList.put("Peru Time", "PET");
        timezoneList.put("Pacific Daylight Time", "PDT");
        timezoneList.put("Papa Time Zone", "P");
        timezoneList.put("Omsk Standard Time", "OMST");
        timezoneList.put("Omsk Summer Time", "OMSST");
        timezoneList.put("Oscar Time Zone", "O");
        timezoneList.put("New Zealand Standard Time", "NZST");
        timezoneList.put("New Zealand Daylight Time", "NZDT");
        timezoneList.put("Niue Time", "NUT");
        timezoneList.put("Newfoundland Standard Time", "NST");
        timezoneList.put("Nepal Time", "NPT");
        timezoneList.put("Novosibirsk Time", "NOVT");
        timezoneList.put("Novosibirsk Summer Time", "NOVST");
        timezoneList.put("Norfolk Time", "NFT");
        timezoneList.put("Newfoundland Daylight Time", "NDT");
        timezoneList.put("New Caledonia Time", "NCT");
        timezoneList.put("November Time Zone", "N");
        timezoneList.put("Malaysia Time", "MYT");
        timezoneList.put("Maldives Time", "MVT");
        timezoneList.put("Mauritius Time", "MUT");
        timezoneList.put("Mountain Standard Time", "MST");
        timezoneList.put("Moscow Standard Time", "MSK");
        timezoneList.put("Moscow Daylight Time", "MSD");
        timezoneList.put("Myanmar Time", "MMT");
        timezoneList.put("Marshall Islands Time", "MHT");
        timezoneList.put("Mitteleurop�ische Zeit", "MEZ");
        timezoneList.put("Mitteleurop�ische Sommerzeit", "MESZ");
        timezoneList.put("Mountain Daylight Time", "MDT");
        timezoneList.put("Mawson Time", "MAWT");
        timezoneList.put("Marquesas Time", "MART");
        timezoneList.put("Magadan Time", "MAGT");
        timezoneList.put("Magadan Summer Time", "MAGST");
        timezoneList.put("Mike Time Zone", "M");
        timezoneList.put("Line Islands Time", "LINT");
        timezoneList.put("Lord Howe Standard Time", "LHST");
        timezoneList.put("Lord Howe Daylight Time", "LHDT");
        timezoneList.put("Lima Time Zone", "L");
        timezoneList.put("Kuybyshev Time", "KUYT");
        timezoneList.put("Korea Standard Time", "KST");
        timezoneList.put("Krasnoyarsk Time", "KRAT");
        timezoneList.put("Krasnoyarsk Summer Time", "KRAST");
        timezoneList.put("Kyrgyzstan Time", "KGT");
        timezoneList.put("Kilo Time Zone", "K");
        timezoneList.put("Japan Standard Time", "JST");
        timezoneList.put("Irish Standard Time", "IST");
        timezoneList.put("India Standard Time", "IST");
        timezoneList.put("Israel Standard Time", "IST");
        timezoneList.put("Iran Standard Time", "IRST");
        timezoneList.put("Irkutsk Time", "IRKT");
        timezoneList.put("Irkutsk Summer Time", "IRKST");
        timezoneList.put("Iran Daylight Time", "IRDT");
        timezoneList.put("Indian Chagos Time", "IOT");
        timezoneList.put("Indochina Time", "ICT");
        timezoneList.put("India Time Zone", "I");
        return timezoneList;
    }
    public static String CovertStringDateToUTCString(String inputDateString, String timezone){
        String strUTC = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            sf.setTimeZone(TimeZone.getTimeZone(timezone));
            Date dateConverted = null;
            if(inputDateString != null){
                dateConverted = sf.parse(inputDateString);
            }
            SimpleDateFormat sfUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            TimeZone timeZoneTZ = TimeZone.getTimeZone("UTC");
            sfUTC.setTimeZone(timeZoneTZ);
            if(dateConverted != null){
                strUTC = sfUTC.format(dateConverted);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strUTC;
    }
    public static String GetStartDate(String timezone){
        String fromDate = null;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        fromDate = ConvertUTCDATE(calendar.getTime());
        return fromDate;
    }

    public static String GetEndDate(String timezone){
        String toDate = null;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        toDate = ConvertUTCDATE(calendar.getTime());
        return toDate;
    }
    public static String ConvertUTCDATE(Date iputDate){
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        //Time in GMT
        String myDate = dateFormatGmt.format(iputDate);
        return myDate;
    }
    public static String CovertUTCStringDateToDefaultString(String inputDateString){
        String strUTC = null;
        try {
            SimpleDateFormat sfutc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            TimeZone timeZoneTZ = TimeZone.getTimeZone("UTC");
            sfutc.setTimeZone(timeZoneTZ);
            Date dateConverted = null;
            if(inputDateString != null){
                dateConverted = sfutc.parse(inputDateString);
            }
            SimpleDateFormat sfDefault = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            if(dateConverted != null){
                strUTC = sfDefault.format(dateConverted);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strUTC;
    }
    public static String formatHHMM(int minutes) {
        if(minutes > 0){
            int hour = minutes / 60;
            int minute = (minutes % 60);
            String h = Integer.toString(hour);
            String m = Integer.toString(minute);
            for(int i2 = 0 ; i2 < 2 ; i2++){

                if (h.length() < 2) {
                    h = "0" + h;
                }
                if (m.length() < 2) {
                    m = "0" + m;
                }
            }
            String convertedTime ="";

            if(minute > 0 && hour > 0)
                convertedTime = h + " hrs and"+"  "+ m+" minutes";
            else if(minute <= 0 && hour > 0)
                convertedTime = convertedTime+ h + " hrs and";
            else if(minute > 0 && hour <= 0)
                convertedTime = convertedTime+""+ m+" minutes";
            else if(minute <= 0 && hour <= 0)
                convertedTime = convertedTime+""+"0 minutes";
            return convertedTime;
        }
        else{
            return "0 minutes";
        }
    }
    public static String getDateStrDisplayHHMM(Date date){
        String dateStr = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("hh:mm a");
            dateStr = "";
            if(date != null){
                dateStr = sf.format(date);
            }
            String shortName = getTimeZone_OffSet_ShortName();

            if(dateStr != null && shortName != null){
                dateStr = dateStr + " " + shortName;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }
    public static String getCurrentDateWithoutTime(String timeZone){
        String currentDate = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            sf.setTimeZone(TimeZone.getTimeZone(timeZone));
            currentDate = sf.format(new Date());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentDate;
    }
}
