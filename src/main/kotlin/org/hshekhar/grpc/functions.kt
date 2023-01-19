package org.hshekhar.grpc

import io.grpc.Status

/**
 * @created 1/19/2023'T'4:15 PM
 * @author Himanshu Shekhar
 **/

private fun <T> handleDynamoException(block: () -> T): T {
    return try {
        block()
    } catch (ex: Exception) {
        throw Status.INTERNAL
            .withCause(ex)
            .withDescription("Internal Error")
            .asException()
    }
}