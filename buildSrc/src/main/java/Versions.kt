import java.io.File
import java.io.FileInputStream
import java.util.*

object Versions {

    fun getProperty(file: File, key: String): String = Properties().apply { load(FileInputStream(file)) }.getProperty(key)

    const val COMPILE_SDK = 32
    const val TARGET_SDK = 32
    const val MIN_SDK = 21

    const val HILT_AGP = "2.42"
    const val KOTLIN = "1.7.0"
}