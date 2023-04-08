package com.nextup.spotifyforartists_20.domain.android

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.Rect
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.nextup.spotifyforartists_20.R
import io.github.muddz.styleabletoast.StyleableToast
import java.text.SimpleDateFormat
import java.util.*

object AndroidUtils {

    fun getAndroidStatusBarHeight(activity: AppCompatActivity): Int {
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) activity.resources.getDimensionPixelSize(resourceId)
        else Rect().apply { activity.window.decorView.getWindowVisibleDisplayFrame(this) }.top
    }

    fun getAndroidNavSize(activity: AppCompatActivity): Int {
        val resourceId = activity.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) activity.resources.getDimensionPixelSize(resourceId)
        else Rect().apply { activity.window.decorView.getWindowVisibleDisplayFrame(this) }.top
    }

    fun AppCompatActivity.startActivity(startActivity: AppCompatActivity) {
        Intent(this, startActivity::class.java).also { intent->
            startActivity(intent)
        }
    }


    fun toast(
        text: String,
        context: Context,
        gravity: Int = Gravity.CENTER,
        cornerRadius: Int = 10,
    ): StyleableToast.Builder {
        val styleableToast = StyleableToast.Builder(context)

        styleableToast .text(text)
            .textColor(Color.WHITE)
            .textBold()
            .backgroundColor(context.resources.getColor(R.color.toast_color))
            .gravity(gravity)
            .cornerRadius(cornerRadius)
            .show()

        return styleableToast
    }

    fun Fragment.startActivity(startActivity: AppCompatActivity)  {
        Intent(requireActivity(), startActivity::class.java).also { intent->
            startActivity(intent)
        }
    }

    fun getCurrentDate(): String {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int =
            calendar.get(Calendar.MONTH)

        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateString: String = dateFormat.format(currentDate)
        return dateString
    }

    fun getCurrentBiggerFormatDate(): String {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int =
            calendar.get(Calendar.MONTH)

        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val dateString: String = dateFormat.format(currentDate)
        return dateString
    }

    fun AppCompatActivity.snackBar(
        text: String,
        shouldHaveAction: Boolean = false,
        actionText: String = "",
        backgroundTint: Int = Color.BLACK,
        textColor: Int = Color.WHITE,
        actionTextColor: Int = Color.BLACK,
        onActionPressed: () -> (Unit) = {}
    ){
        val snackBar = Snackbar.make(findViewById(android.R.id.content), "Photo Taken", Snackbar.LENGTH_SHORT)
        snackBar.setBackgroundTint(backgroundTint)
        snackBar.setTextColor(textColor)
        if (shouldHaveAction){
            snackBar.setActionTextColor(actionTextColor)
            snackBar.setAction("Show"){
                onActionPressed.invoke()
            }
        }
        snackBar.show()
    }

    fun Fragment.snackBar(
        text: String,
        shouldHaveAction: Boolean = false,
        actionText: String = "",
        backgroundTint: Int = Color.BLACK,
        textColor: Int = Color.WHITE,
        actionTextColor: Int = Color.BLACK,
        onActionPressed: () -> (Unit) = {}
    ){
        val snackBar = Snackbar.make(requireActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT)
        snackBar.setBackgroundTint(backgroundTint)
        snackBar.setTextColor(textColor)
        if (shouldHaveAction){
            snackBar.setActionTextColor(actionTextColor)
            snackBar.setAction(actionText){
                onActionPressed.invoke()
            }
        }
        snackBar.show()
    }


    private fun isAppInForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val packageName = context.packageName

        val runningTasks = activityManager.getRunningTasks(1)

        if (runningTasks.isEmpty()) {
            return false
        }

        val topTask = runningTasks[0]
        return topTask.topActivity?.packageName == packageName
    }





}