package com.odk.pjt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotionResponse {
    public String object;           // 응답 객체 타입 (항상 "list")
    public List<NotionResult> results; // 검색 결과 리스트 (페이지 및 데이터베이스 객체들)
    public String next_cursor;      // 다음 페이지를 가져오기 위한 커서 ID (없으면 null)
    public boolean has_more;        // 추가 결과가 더 있는지 여부
    public String type;             // 결과 리스트의 타입
    public String request_id;       // 노션 API 요청 고유 식별자
}
