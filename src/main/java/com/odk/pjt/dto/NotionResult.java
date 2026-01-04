package com.odk.pjt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotionResult {
    public String object;           // 객체 타입 ("page" 또는 "database")
    public String id;               // 해당 객체의 고유 ID
    public String created_time;     // 생성 일시
    public String last_edited_time; // 최종 수정 일시
    public User created_by;         // 생성자 정보
    public User last_edited_by;     // 최종 수정자 정보
    public Cover cover;             // 페이지/DB 커버 이미지 정보
    public Icon icon;               // 페이지/DB 아이콘 정보 (이모지 등)
    public Parent parent;           // 부모 객체 정보 (속한 페이지나 워크스페이스 등)
    public boolean archived;        // 아카이브(삭제 대기) 여부
    public boolean in_trash;        // 휴지통에 있는지 여부
    public String url;              // 노션에서 접근 가능한 해당 객체의 URL
    public String public_url;       // 공개 페이지인 경우의 URL
    public Map<String, Property> properties; // 페이지의 속성값들 (Key: 속성명)

    // 데이터베이스 객체 전용 필드
    public List<RichText> title;    // 데이터베이스의 제목
    public boolean is_inline;       // 인라인 데이터베이스 여부

    // 페이지 객체 전용 필드
    @JsonProperty("is_locked")
    public boolean is_locked;       // 페이지 잠금 여부
}
