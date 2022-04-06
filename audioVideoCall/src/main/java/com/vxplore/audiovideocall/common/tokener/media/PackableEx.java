package com.vxplore.audiovideocall.common.tokener.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
