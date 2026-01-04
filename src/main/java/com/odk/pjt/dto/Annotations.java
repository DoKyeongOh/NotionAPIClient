package com.odk.pjt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Annotations {
    public boolean bold;           // 굵게 처리 여부
    public boolean italic;         // 기울임 처리 여부
    public boolean strikethrough;  // 취소선 처리 여부
    public boolean underline;      // 밑줄 처리 여부
    public boolean code;           // 인라인 코드 스타일 여부
    public String color;           // 텍스트 및 배경 색상
}
