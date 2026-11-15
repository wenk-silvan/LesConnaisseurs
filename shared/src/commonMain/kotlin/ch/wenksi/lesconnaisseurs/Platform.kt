package ch.wenksi.lesconnaisseurs

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform