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
package com.kakao.sdk.link.model

import com.google.gson.JsonObject
import com.kakao.sdk.link.Constants

/**
 * @suppress
 * @author kevin.kang. Created on 09/04/2019..
 */
class KakaoLinkAttachment(
    val lv: String = Constants.LINKVER_40,
    val av: String = Constants.LINKVER_40,
    val ak: String,
    val P: JsonObject? = null,
    val C: JsonObject? = null,
    val ti: Long,
    val ta: JsonObject? = null,
    val extras: JsonObject
)