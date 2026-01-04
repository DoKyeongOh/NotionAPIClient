package com.odk.pjt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Property {
    public String id; // 속성의 고유 ID
    public String name; // 속성 이름 (예: "마감일", "상태")
    public String type; // 속성의 타입 (date, rich_text, select 등)
    public String description; // 속성에 대한 설명

    // 노션 API 특성상 DB 정의와 페이지 값이 객체/배열로 혼용되므로 JsonNode로 처리
    public JsonNode date; // 날짜 데이터 (DB 정의 시 {}, 페이지 값 시 {start, end})
    public JsonNode rich_text; // 리치 텍스트 데이터 (DB 정의 시 {}, 페이지 값 시 [])
    public JsonNode number; // 숫자 데이터 (DB 정의 시 {format}, 페이지 값 시 Double)
    public JsonNode people; // 사용자 데이터 (DB 정의 시 {}, 페이지 값 시 [])
    public JsonNode select; // 단일 선택 데이터 (DB 정의 시 {options}, 페이지 값 시 {id, name})
    public JsonNode status; // 상태 데이터 (DB 정의 시 {options, groups}, 페이지 값 시 {id, name})
    public JsonNode title; // 제목 데이터 (DB 정의 시 {}, 페이지 값 시 [])
    public JsonNode multi_select; // 다중 선택 데이터 (DB 정의 시 {options}, 페이지 값 시 [])
    public User created_by; // 생성자 정보 (항상 객체 구조)

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DateValue {
        public String start; // 시작 날짜 및 시간
        public String end; // 종료 날짜 및 시간 (없으면 null)
        public String time_zone; // 시간대 정보
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SelectValue {
        public String id; // 선택 옵션의 고유 ID
        public String name; // 선택 옵션의 이름
        public String color; // 옵션의 배경 색상
        public String description; // 옵션 설명
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatusValue {
        public String id; // 상태 값의 고유 ID
        public String name; // 상태 값의 이름 (예: "진행중")
        public String color; // 상태의 배경 색상
        public String description; // 상태 설명
        public List<SelectValue> options; // 상태에 포함된 전체 옵션 리스트
        public List<StatusGroup> groups; // 상태 그룹 리스트 (To-do, In progress 등)
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatusGroup {
        public String id; // 상태 그룹의 고유 ID
        public String name; // 상태 그룹의 이름
        public String color; // 그룹 색상
        public List<String> option_ids; // 이 그룹에 속한 옵션 ID 리스트
    }
}
