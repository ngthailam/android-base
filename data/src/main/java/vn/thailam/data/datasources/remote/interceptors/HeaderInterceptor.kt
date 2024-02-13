package vn.thailam.data.datasources.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Can inject other classes here to get the needed informations
 */
class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("appid", "hello")
                .addHeader("deviceplatform", "android")
                .build()
        )
    }
}
