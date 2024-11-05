
package sk.amir.maps

import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlin.properties.Delegates

internal var appContext: Context by Delegates.notNull()
internal actual fun openURL(string: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(string))
    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    appContext.startActivity(browserIntent)
}