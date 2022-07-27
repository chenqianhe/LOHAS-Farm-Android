package com.example.lohasfarm.ui.main.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.lohasfarm.R

enum class Tabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val selectIcon: Int
) {
    FARM_PAGE(R.string.farm_page, R.drawable.ic_farm_page, R.drawable.ic_farm_page_selected),
    ACTIVITY_PAGE(R.string.activity, R.drawable.ic_activity_page, R.drawable.ic_activity_page_selected),
    MESSAGE_PAGE(R.string.message, R.drawable.ic_message_page_redpoint, R.drawable.ic_message_page_redpoint_selected),
    MINE_PAGE(R.string.mine, R.drawable.ic_mine_page, R.drawable.ic_mine_page_selected)

}