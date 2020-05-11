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
package com.kakao.sdk.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kakao.sdk.common.SdkLogger
import com.kakao.sdk.network.rx
import com.kakao.sdk.sample.common.DebugFragment
import com.kakao.sdk.sample.common.OpenFragment
import io.reactivex.subjects.ReplaySubject

class MainActivity : AppCompatActivity() {
    private val logSubject = ReplaySubject.createWithSize<Any>(100)
    val logObservable = logSubject.hide()
    val debugFragment = DebugFragment.newInstance(logObservable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SdkLogger.rx.observable.doOnNext { Log.d("KakaoSdk", it.toString()) }
            .doOnNext { logSubject.onNext(it) }
            .subscribe()

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, OpenFragment.newInstance(Bundle().apply {
            putLong("customMemo", 19388)
            putLong("customMessage", 19388)
        })).disallowAddToBackStack().commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_debug -> {
                if (debugFragment.isAdded) return true
                supportFragmentManager.beginTransaction()
                    .addToBackStack("main")
                    .add(
                        R.id.container,
                        debugFragment
                    ).commit()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
