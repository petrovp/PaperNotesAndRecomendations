package com.brko;

import com.google.common.base.Charsets;

import java.nio.charset.Charset;

/**
 * Class with constants that are used in multiple files in the application.
 *
 * Created by Petre on 9/4/2016.
 */
public final class GlobalConstants {

    private GlobalConstants() {

    }

    public static final String CHARSET = Charsets.UTF_8.name();

    public static final String ARXIV_BASE_URL = "https://arxiv.org";

    public static final String ARXIV_ABSTRACTS_BASE_URL = ARXIV_BASE_URL + "/abs";
}
