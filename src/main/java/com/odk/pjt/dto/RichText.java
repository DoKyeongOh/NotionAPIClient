package com.odk.pjt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RichText {
    public String type;             // 리치 텍스트 타입 (예: text, mention, equation)
    public Text text;               // 실제 텍스트 내용 및 링크 정보
    public Annotations annotations; // 텍스트 서식 (굵게, 색상 등)
    public String plain_text;       // 서식이 제거된 순수 텍스트 내용
    public String href;             // 텍스트에 연결된 URL

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Text {
        public String content;      // 실제 문자열 내용
        public Object link;         // 링크 정보 (있을 경우 url 포함 객체)
    }
}
