
package sk.amir.maps

import platform.Foundation.NSLocale
import platform.Foundation.NSLocaleLanguageCode
import platform.Foundation.NSLocaleLanguageDirectionRightToLeft
import platform.Foundation.NSURL
import platform.Foundation.characterDirectionForLanguage
import platform.Foundation.currentLocale
import platform.UIKit.UIApplication

internal fun isDeviceLanguageRTL(): Boolean {
    val language = NSLocale.currentLocale.objectForKey(NSLocaleLanguageCode) as? String ?: return false
    return NSLocale.characterDirectionForLanguage(language) == NSLocaleLanguageDirectionRightToLeft
}

internal actual fun openURL(string: String) {
    val url = NSURL(string = string)
    UIApplication.sharedApplication().openURL(url)
}