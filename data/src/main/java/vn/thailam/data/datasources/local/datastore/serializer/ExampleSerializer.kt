package vn.thailam.data.datasources.local.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import vn.thailam.data.datasources.local.datastore.entity.ExampleEntityProtoWrapper
import java.io.InputStream
import java.io.OutputStream

object ExampleEntitySerializer : Serializer<ExampleEntityProtoWrapper> {
    override val defaultValue: ExampleEntityProtoWrapper
        get() = ExampleEntityProtoWrapper.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ExampleEntityProtoWrapper {
        try {
            return ExampleEntityProtoWrapper.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    }

    override suspend fun writeTo(t: ExampleEntityProtoWrapper, output: OutputStream) {
        t.writeTo(output)
    }
}
