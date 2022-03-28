package com.metaverse.station.back.oauth.info;



import com.metaverse.station.back.oauth.domain.ProviderType;
import com.metaverse.station.back.oauth.info.impl.GoogleOAuth2UserInfo;
import com.metaverse.station.back.oauth.info.impl.KakaoOAuth2UserInfo;
import com.metaverse.station.back.oauth.info.impl.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static com.metaverse.station.back.oauth.info.OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
