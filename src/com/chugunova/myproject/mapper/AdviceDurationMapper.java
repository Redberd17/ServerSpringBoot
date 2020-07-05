package com.chugunova.myproject.mapper;

public class AdviceDurationMapper {
    public static String GET_ADVICE_DURATION = "select advice_dur_name as adviceDurName, advice_dur_value as adviceDurValue, advice_dur_text as adviceDurText, advice_dur_grade as adviceDurGrade\n" +
            "from advice_duration\n" +
            "order by id_advice_dur";
}
