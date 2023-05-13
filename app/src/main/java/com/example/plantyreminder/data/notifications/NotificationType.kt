package com.example.plantyreminder.data.notifications

import androidx.annotation.StringRes
import com.example.plantyreminder.R

enum class NotificationType(@StringRes val nameId: Int = R.string.app_name, @StringRes val descriptionId: Int) {
    Reminder(descriptionId = R.string.notification_reminder_desc)
}
