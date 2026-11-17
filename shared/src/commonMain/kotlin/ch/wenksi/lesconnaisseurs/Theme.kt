package ch.wenksi.lesconnaisseurs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import lesconnaisseurs.shared.generated.resources.PlayfairDisplay_Medium
import lesconnaisseurs.shared.generated.resources.PlayfairDisplay_Regular
import lesconnaisseurs.shared.generated.resources.PlayfairDisplay_SemiBold
import lesconnaisseurs.shared.generated.resources.PlusJakartaSans_Regular
import lesconnaisseurs.shared.generated.resources.PlusJakartaSans_SemiBold
import lesconnaisseurs.shared.generated.resources.Res
import org.jetbrains.compose.resources.Font

private val Surface = Color(0xFF121314)
private val SurfaceDim = Color(0xFF121314)
private val SurfaceBright = Color(0xFF38393A)
private val SurfaceContainerLowest = Color(0xFF0D0E0F)
private val SurfaceContainerLow = Color(0xFF1B1C1D)
private val SurfaceContainer = Color(0xFF1F2021)
private val SurfaceContainerHigh = Color(0xFF292A2B)
private val SurfaceContainerHighest = Color(0xFF343536)
private val OnSurface = Color(0xFFE3E2E3)
private val OnSurfaceVariant = Color(0xFFD1C5B8)
private val InverseSurface = Color(0xFFE3E2E3)
private val InverseOnSurface = Color(0xFF303031)
private val Outline = Color(0xFF998F83)
private val OutlineVariant = Color(0xFF4D463C)
private val SurfaceTint = Color(0xFFE0C298)
private val Primary = Color(0xFFE2C399)
private val OnPrimary = Color(0xFF402D0F)
private val PrimaryContainer = Color(0xFFC5A880)
private val OnPrimaryContainer = Color(0xFF513D1D)
private val InversePrimary = Color(0xFF725B38)
private val Secondary = Color(0xFFE6C27E)
private val OnSecondary = Color(0xFF412D00)
private val SecondaryContainer = Color(0xFF5B430A)
private val OnSecondaryContainer = Color(0xFFD3B06F)
private val Tertiary = Color(0xFFC7C7CE)
private val OnTertiary = Color(0xFF2E3035)
private val TertiaryContainer = Color(0xFFABACB2)
private val OnTertiaryContainer = Color(0xFF3E4045)
private val Error = Color(0xFFFFB4AB)
private val OnError = Color(0xFF690005)
private val ErrorContainer = Color(0xFF93000A)
private val OnErrorContainer = Color(0xFFFFDAD6)

private val DarkColorScheme = darkColorScheme(
    surface = Surface,
    surfaceDim = SurfaceDim,
    surfaceBright = SurfaceBright,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    inverseSurface = InverseSurface,
    inverseOnSurface = InverseOnSurface,
    outline = Outline,
    outlineVariant = OutlineVariant,
    surfaceTint = SurfaceTint,
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    inversePrimary = InversePrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,
    background = Surface,
    onBackground = OnSurface
)

val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(2.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(8.dp),
    extraLarge = RoundedCornerShape(12.dp)
)


@Composable
fun LesConnaisseursAppTheme(
    content: @Composable () -> Unit
) {
    val PlayfairDisplay = FontFamily(
        Font(Res.font.PlayfairDisplay_Regular, weight = FontWeight.Normal),
        Font(Res.font.PlayfairDisplay_Medium, weight = FontWeight.Medium),
        Font(Res.font.PlayfairDisplay_SemiBold, weight = FontWeight.SemiBold),
    )

    val PlusJakartaSans = FontFamily(
        Font(Res.font.PlusJakartaSans_Regular, weight = FontWeight.Normal),
        Font(Res.font.PlusJakartaSans_SemiBold, weight = FontWeight.SemiBold),
    )

    val appTypography = Typography(
        displayLarge = TextStyle(
            fontFamily = PlayfairDisplay,
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 48.sp,
            letterSpacing = (-0.02).em
        ),
        displayMedium = TextStyle(
            fontFamily = PlayfairDisplay,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp,
            letterSpacing = (-0.01).em
        ),
        headlineLarge = TextStyle(
            fontFamily = PlayfairDisplay,
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 36.sp,
            letterSpacing = (-0.01).em
        ),
        headlineMedium = TextStyle(
            fontFamily = PlayfairDisplay,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 30.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = PlayfairDisplay,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 24.sp,
            letterSpacing = 0.01.em
        ),
        titleMedium = TextStyle(
            fontFamily = PlusJakartaSans,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 22.sp,
            letterSpacing = 0.01.em
        ),
        bodyLarge = TextStyle(
            fontFamily = PlusJakartaSans,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = PlusJakartaSans,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
            letterSpacing = 0.01.em
        ),
        labelMedium = TextStyle(
            fontFamily = PlusJakartaSans,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 16.sp,
            letterSpacing = 0.12.em
        ),
        labelSmall = TextStyle(
            fontFamily = PlusJakartaSans,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 14.sp,
            letterSpacing = 0.16.em
        )
    )

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = appTypography,
        shapes = AppShapes,
        content = content
    )
}
