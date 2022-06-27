plugins {
    id("java-platform")
    id("maven-publish")
}

val appCompat = "1.4.2"
val constraintLayout = "2.1.4"
val coreKtx = "1.8.0"
val espressoCore = "3.4.0"
val extJunit = "1.1.3"
val hilt = Versions.HILT_AGP
val junit = "4.13.2"
val lifecycle = "2.4.1"
val material = "1.6.1"
val timber = "5.0.1"

dependencies {
    constraints {
        api("${Libs.APPCOMPAT}:$appCompat")
        api("${Libs.CONSTRAINT_LAYOUT}:$constraintLayout")
        api("${Libs.CORE_KTX}:$coreKtx")
        api("${Libs.ESPRESSO_CORE}:$espressoCore")
        api("${Libs.EXT_JUNIT}:$extJunit")
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.LIFECYCLE_LIVEDATA_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEWMODEL_KTX}:$lifecycle")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.TIMBER}:$timber")
    }
}
