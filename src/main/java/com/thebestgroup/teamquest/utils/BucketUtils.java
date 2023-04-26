package com.thebestgroup.teamquest.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BucketUtils {

    public String USER_PLACEHOLDER = ":user";

    public String setUser(String bucket, String user) {

        return bucket.replace(USER_PLACEHOLDER, user);
    }
}
