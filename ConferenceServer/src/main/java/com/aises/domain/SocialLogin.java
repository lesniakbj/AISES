package com.aises.domain;

import com.aises.domain.enums.SocialLoginType;

/**
 * Created by Brendan on 8/1/2016.
 *
 * Represents a Social Media Login. Has a
 * provider type and an ID.
 */
public class SocialLogin {
    private String socialMediaId;
    private SocialLoginType provider;

    public String getSocialMediaId() {
        return socialMediaId;
    }
    public SocialLoginType getProvider() {
        return provider;
    }

    public void setSocialMediaId(String socialMediaId) {
        this.socialMediaId = socialMediaId;
    }
    public void setProvider(SocialLoginType provider) {
        this.provider = provider;
    }
}
