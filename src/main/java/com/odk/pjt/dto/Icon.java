package com.odk.pjt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Icon {
    public String type;             // 아이콘 타입 (emoji 또는 external)
    public Emoji emoji;             // 이모지 아이콘일 경우의 정보
    public External external;       // 외부 이미지 아이콘일 경우의 정보

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Emoji {
        public String emoji;        // 이모지 문자 (예: 🍎)
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class External {
        public String url;          // 외부 이미지 파일의 URL
    }
}
