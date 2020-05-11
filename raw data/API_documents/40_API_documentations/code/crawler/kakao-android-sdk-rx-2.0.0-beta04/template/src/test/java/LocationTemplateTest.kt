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
package com.kakao.sdk.template

import com.kakao.sdk.common.KakaoJson
import org.junit.jupiter.api.Test

class LocationTemplateTest {
    @Test
    fun empty() {
        val template = LocationTemplate(
            address = "address",
            content = Content("title", "imageUrl", Link())
        )
        println(KakaoJson.pretty.toJson(template, LocationTemplate::class.java))
    }
}