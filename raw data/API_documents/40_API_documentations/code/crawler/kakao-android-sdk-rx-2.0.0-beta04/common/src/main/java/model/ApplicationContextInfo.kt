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
package com.kakao.sdk.common.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.JsonObject
import com.kakao.sdk.common.util.Utility

/**
 * @author kevin.kang. Created on 20/02/2019..
 */
class ApplicationContextInfo(
    context: Context,
    appKey: String
) : ApplicationInfo, ContextInfo {
    private val mClientId: String = appKey

    private val mKaHeader: String = Utility.getKAHeader(context)
    private val mKeyHash: String = Utility.getKeyHash(context)


    private val mExtras: JsonObject = Utility.getExtras(context)

    private val mSharedPreferences: SharedPreferences =
        context.getSharedPreferences(appKey, Context.MODE_PRIVATE)
    private val mAppVer: String =
        context.packageManager.getPackageInfo(context.packageName, 0).versionName
    private val mSalt: ByteArray = Utility.androidId(context)

    override val appKey: String
        get() = mClientId

    override val kaHeader: String
        get() = mKaHeader
    override val signingKeyHash: String
        get() = mKeyHash
    override val extras: JsonObject
        get() = mExtras
    override val appVer: String
        get() = mAppVer
    override val salt: ByteArray
        get() = mSalt
    val sharedPreferences: SharedPreferences
        get() = mSharedPreferences
}