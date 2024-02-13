package vn.thailam.data.datasources.local.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import vn.thailam.data.datasources.local.datastore.entity.UserEntity
import java.io.InputStream
import java.io.OutputStream

object UserEntitySerializer : Serializer<UserEntity> {
    override val defaultValue: UserEntity
        get() = UserEntity.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserEntity {
        try {
            return UserEntity.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserEntity, output: OutputStream) {
        t.writeTo(output)
    }
}
