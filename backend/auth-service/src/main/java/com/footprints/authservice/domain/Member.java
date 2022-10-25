package com.footprints.authservice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@ToString
//public class Member extends BaseEntity {
    public class Member  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String nickname;
    private String kakaoProviderId;
    private String region;


    public void updateProviderId(String provider, String providerId){

        if(provider.equals("kakao")){
            this.kakaoProviderId = providerId;
        }
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

}