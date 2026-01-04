package com.odk.pjt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cover {
    public String type;             // 커버 이미지 타입 (항상 "external" 또는 "file")
    public External external;       // 외부 이미지 URL 정보

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class External {
        public String url;          // 커버 이미지의 공개 URL
    }
}
