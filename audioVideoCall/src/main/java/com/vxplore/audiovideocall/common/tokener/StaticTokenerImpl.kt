package com.vxplore.agoraaudiocall.tokener

import com.vxplore.audiovideocall.agoraaudiocall.Credential
import com.vxplore.audiovideocall.agoraaudiocall.TokenBuilder
import com.vxplore.audiovideocall.common.tokener.Tokener

class StaticTokenerImpl(credential: Credential, tokenBuilder: TokenBuilder) : Tokener {
    override suspend fun new(): String {
        return "00613622905826d46538beb9bd1d96c83b1IADLjTMOaOqkNsuH2c3M3/ac1D0TjIMYGOrvl/seQ8VY1tm4ZkoAAAAAEABrDG+dZ3tJYgEAAQBne0li"
    }
}