/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * User.kt is part of Kizzy
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.kizzy.domain.model.user

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class User(
    @SerialName("bio")
    val bio: String? = "",
    @SerialName("nitro")
    val nitro: Boolean? = false,
    @SerialName("accent_color")
    val accentColor: Int? = null,
    @SerialName("avatar")
    val avatar: String? = null,
    @SerialName("avatar_decoration")
    val avatarDecoration: String? = null,
    @SerialName("badges")
    val badges: List<Badge>? = null,
    @SerialName("banner")
    val banner: String? = null,
    @SerialName("banner_color")
    val bannerColor: String? = null,
    @SerialName("discriminator")
    val discriminator: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("public_flags")
    val publicFlags: Int? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("special")
    val special: String? = null,
    @SerialName("verified")
    val verified: Boolean = false,
    @SerialName("global_name")
val globalName: String? = null
) {
    fun getAvatarImage(): String {
        return if (avatar?.startsWith("a_") == true)
            "${DISCORD_CDN}/avatars/${id}/${avatar}.gif?size=512"
        else
            "${DISCORD_CDN}/avatars/${id}/${avatar}.png?size=512"
    }
    fun getStaticAvatarImage(): String {
        return "${DISCORD_CDN}/avatars/${id}/${avatar}.png?size=512"
    }
    fun getBannerImage(): String? {
        if (banner.isNullOrEmpty()) return null
        return if (banner.startsWith("a_"))
            "$DISCORD_CDN/banners/${id}/${banner}.gif?size=480"
        else
            "$DISCORD_CDN/banners/${id}/${banner}.png?size=480"
    }
    fun getStaticBannerImage(): String? {
        if (banner.isNullOrEmpty()) return null
        return "$DISCORD_CDN/banners/${id}/${banner}.png?size=480"
    }

    fun getAllBadges(): List<Badge> {
        val result = mutableListOf<Badge>()
        val flags = publicFlags ?: 0

        // Badges computed from publicFlags bitmask
        val flagBadges = listOf(
            1          to Badge("${BADGE_CDN}5e74e9b61934fc1f67c65515d1f7e60d.png", "Discord Staff"),
            2          to Badge("${BADGE_CDN}3f9748e53446a137a052f3454e2de41e.png", "Discord Partner"),
            4          to Badge("${BADGE_CDN}bf01d1073931f921909045f3a39fd264.png", "HypeSquad Events"),
            8          to Badge("${BADGE_CDN}44539473a4cf7343d6bb3d154cce31ce.png", "Bug Hunter"),
            64         to Badge("${BADGE_CDN}8a88d63823d8a71cd5e390baa45efa02.png", "HypeSquad Bravery"),
            128        to Badge("${BADGE_CDN}011940fd013082d85d96680e1f40dafa.png", "HypeSquad Brilliance"),
            256        to Badge("${BADGE_CDN}3aa41de486fa12454c3761e8e223442e.png", "HypeSquad Balance"),
            512        to Badge("${BADGE_CDN}7060786766c9c840eb3019e725d2b358.png", "Early Supporter"),
            16384      to Badge("${BADGE_CDN}848f79194d4be5ff5f81505cbd0ce1e6.png", "Bug Hunter Level 2"),
            131072     to Badge("${BADGE_CDN}6df5892e0f35b051f8b61eace34f4967.png", "Early Verified Bot Developer"),
            262144     to Badge("${BADGE_CDN}fee1624003e2fee35cb398e125dc479a.png", "Moderator Programs Alumni"),
            4194304    to Badge("${BADGE_CDN}6bdc42827a38498929a4920da12695d9.png", "Active Developer"),
        )
        flagBadges.forEach { (flag, badge) ->
            if (flags and flag != 0) result.add(badge)
        }

        // Nitro badge
        if (nitro == true) result.add(Badge("${BADGE_CDN}2ba85e8026a8614b640c2837bcdfe21b.png", "Nitro"))

        // Extra badges returned by the API (e.g. Legacy Username, Quest) — add if not already included
        badges?.forEach { apiBadge ->
            if (result.none { it.icon == apiBadge.icon }) result.add(apiBadge)
        }

        return result
    }
}

private const val DISCORD_CDN = "https://cdn.discordapp.com"
private const val BADGE_CDN = "https://cdn.discordapp.com/badge-icons/"