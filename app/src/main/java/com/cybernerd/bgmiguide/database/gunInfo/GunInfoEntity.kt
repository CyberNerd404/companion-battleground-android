package com.cybernerd.bgmiguide.database.gunInfo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "gun_info_table")
class GunInfoEntity(
        @PrimaryKey(autoGenerate = true)
        var gunId: Int = 0,
        var gunName: String? = ""
)