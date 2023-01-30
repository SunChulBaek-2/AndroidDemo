import java.io.File
import java.io.FileInputStream
import java.util.*

object Versions {

    fun getProperty(file: File, key: String): String = Properties().apply { load(FileInputStream(file)) }.getProperty(key)

    const val COMPILE_SDK = 33
    const val TARGET_SDK = 33
    const val MIN_SDK = 21

    const val HILT_AGP = "2.44.2"
    const val KOTLIN = "1.7.20"
    const val NAVIGATION = "2.5.3"
}