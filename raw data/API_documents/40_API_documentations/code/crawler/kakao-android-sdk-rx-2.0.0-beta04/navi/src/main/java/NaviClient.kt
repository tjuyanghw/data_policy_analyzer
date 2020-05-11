/*
  Copyright 2019 Kakao Corp.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.kakao.sdk.navi

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.kakao.sdk.common.*
import com.kakao.sdk.navi.model.KakaoNaviParams
import com.kakao.sdk.common.KakaoJson
import com.kakao.sdk.common.model.ApplicationInfo
import com.kakao.sdk.common.model.ContextInfo
import com.kakao.sdk.navi.model.Location
import com.kakao.sdk.navi.model.NaviOptions

/**
 * 카카오내비 API 호출을 담당하는 클래스
 *
 * @author kevin.kang. Created on 18/02/2019..
 */
class NaviClient(
    private val applicationInfo: ApplicationInfo = KakaoSdk.applicationContextInfo,
    private val contextInfo: ContextInfo = KakaoSdk.applicationContextInfo
) {

    /**
     * 카카오내비 앱 설치 여부.
     *
     * @return true if installed, false otherwise.
     */
    fun isKakaoNaviInstalled(context: Context): Boolean {
        val navi = context.packageManager.getLaunchIntentForPackage(Constants.NAVI_PACKAGE) != null
        val lgNavi =
            context.packageManager.getLaunchIntentForPackage(Constants.LG_NAVI_PACKAGE) != null
        return navi || lgNavi
    }

    /**
     * 웹 길안내 URL을 얻습니다.
     * 획득한 URL을 브라우저에 요청하면 카카오내비 앱이 설치되지 않은 환경에서도 길안내를 받을 수 있습니다.
     *
     * @see com.kakao.sdk.common.util.KakaoCustomTabsClient
     */
    @JvmOverloads
    fun navigateUrl(
        destination: Location,
        option: NaviOptions? = null,
        viaList: List<Location>? = null
    ): Uri =
        baseUriBuilder(
            KakaoNaviParams(
                destination,
                option,
                viaList
            )
        ).path("${Constants.NAVIGATE}.html").build()

    /**
     * 카카오내비 장소 공유 인텐트를 리턴합니다. 리턴된 인텐트를 실행하면 카카오내비 앱이 열립니다.
     */
    @JvmOverloads
    fun shareDestinationIntent(
        destination: Location,
        options: NaviOptions? = null,
        viaList: List<Location>? = null
    ): Intent {
        val uri = baseUriBuilder(
            KakaoNaviParams(
                destination,
                options,
                viaList
            )
        ).scheme(Constants.NAVI_SCHEME).authority(Constants.SHARE_POI).build()
        return Intent(Intent.ACTION_VIEW, uri)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }

    /**
     * 카카오내비 길안내 인텐트를 리턴합니다. 리턴된 인텐트를 실행하면 카카오내비 앱이 열립니다.
     */
    @JvmOverloads
    fun navigateIntent(
        destination: Location,
        option: NaviOptions? = null,
        viaList: List<Location>? = null
    ): Intent {
        val uri = baseUriBuilder(
            KakaoNaviParams(
                destination,
                option,
                viaList
            )
        ).scheme(Constants.NAVI_SCHEME).authority(Constants.NAVIGATE).build()
        return Intent(Intent.ACTION_VIEW, uri)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }

    private fun baseUriBuilder(
        params: KakaoNaviParams
    ): Uri.Builder = Uri.Builder()
        .scheme(Constants.NAVI_WEB_SCHEME)
        .authority(KakaoSdk.serverHosts.navi)
        .appendQueryParameter(Constants.PARAM, KakaoJson.toJson(params))
        .appendQueryParameter(Constants.APIVER, Constants.APIVER_10)
        .appendQueryParameter(Constants.APPKEY, applicationInfo.appKey)
        .appendQueryParameter(com.kakao.sdk.common.Constants.EXTRAS, contextInfo.extras.toString())

    companion object {
        @JvmStatic
        val instance by lazy { NaviClient() }
    }
}