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
package com.kakao.sdk.sample.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kakao.sdk.sample.common.databinding.ItemFriendBinding

/**
 * @author kevin.kang. Created on 2019-10-07..
 */
class FriendsAdapter(val items: List<PickerItem>) :
    RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class FriendViewHolder(val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PickerItem) {
            binding.item = item

            binding.checkbox.setOnClickListener {
                item.checked = !item.checked
            }
        }
    }
}

