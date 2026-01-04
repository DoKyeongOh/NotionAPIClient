package com.odk.pjt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public String object; // 객체 타입 (항상 "user")
    public String id; // 사용자 고유 ID
    public String name; // 사용자 이름
    public String avatar_url; // 사용자 프로필 이미지 URL
    public String type; // 사용자 타입 (person 또는 bot)
    public Person person; // 사람 타입일 경우의 상세 정보 (이메일 등)

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Person {
        public String email; // 사용자의 이메일 주소
    }
}
