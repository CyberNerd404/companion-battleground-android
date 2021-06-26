package com.cybernerd404.bgmiguide.model

data class GunsModelItem(
    val ammo_id: Int,
    val capacity: Int,
    val damage: Damage,
    val grip_ids: List<Int>,
    val id: Int,
    val image_url: String,
    val loop_ids: List<Int>,
    val magazine_ids: List<Int>,
    val magazine_size: Int,
    val muzzle_ids: List<Int>,
    val name: String,
    val range: Int,
    val rate: Int,
    val reload: Int,
    val sight_ids: List<Int>,
    val stability: Int,
    val stock_ids: List<Int>,
    val type: String
)