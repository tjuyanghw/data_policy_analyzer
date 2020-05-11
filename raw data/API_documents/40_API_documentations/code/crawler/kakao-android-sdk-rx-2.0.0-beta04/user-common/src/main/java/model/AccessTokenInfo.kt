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
package com.kakao.sdk.user.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/** 토큰 정보 요청 API 응답 클래스
 *
 * @property id 사용자 아이디
 * @property expiresInMillis 해당 access token의 남은 만료시간 (단위: milli-second)
 *
 * @author kevin.kang. Created on 2018. 3. 24..
 */
@Parcelize
data class AccessTokenInfo(
    val id: Long,
    @SerializedName("expiresInMillis") val expiresInMillis: Long
) : Parcelable