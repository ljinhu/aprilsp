package com.jimbolix.april.authorization.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/22 10:11
 * @Description:
 */
public class AprilOauthExceptionSerilizer extends StdSerializer<AprilOauthException> {

    public AprilOauthExceptionSerilizer() {
        super(AprilOauthException.class);
    }

    @Override
    public void serialize(AprilOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(e.getR());
    }
}