package org.hshekhar.grpc

import net.devh.boot.grpc.server.service.GrpcService
import org.hshekhar.PingApiGrpcKt
import org.hshekhar.PingRequest
import org.hshekhar.PingResponse
import org.slf4j.LoggerFactory

/**
 * @created 1/19/2023'T'3:36 PM
 * @author Himanshu Shekhar
 **/

@GrpcService
class PingApiImpl: PingApiGrpcKt.PingApiCoroutineImplBase() {
    companion object {
        private val LOG = LoggerFactory.getLogger(PingApiImpl::class.java)
    }

    override suspend fun get(request: PingRequest): PingResponse {
        LOG.debug("entry: get($request)")
        return PingResponse.newBuilder()
            .addAllMessage(
                (0 until request.times).map { index -> "Ping_$index" }
            )
            .build()
    }
}