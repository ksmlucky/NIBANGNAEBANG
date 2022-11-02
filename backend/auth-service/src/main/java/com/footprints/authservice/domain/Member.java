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
    public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nickname;

    private String kakaoProvider;

    private String kakaoProviderId;

    private String googleProvider;

    private String googleProviderId;

    private String region;
    private String di;


    public void updateProviderAndProviderId(String provider, String providerId){

        if(provider.equals("kakao")){
            this.kakaoProvider= provider;
            this.kakaoProviderId = providerId;
        }else{
            this.googleProvider =provider;
            this.googleProviderId = providerId;
        }
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

}