package com.odk.pjt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Parent {
    public String type;             // 부모 객체의 타입 (database_id, page_id, workspace 등)
    public String database_id;      // 부모가 데이터베이스일 경우의 고유 ID
    public String page_id;          // 부모가 페이지일 경우의 고유 ID
    public boolean workspace;       // 부모가 워크스페이스인 경우 true
    public String data_source_id;   // 데이터 소스 ID (특정 통합 환경에서 사용)
}
