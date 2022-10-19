package com.example.tryaq.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFF017EFF)
val Purple500 = Color(0xFF017EFF)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)
val GeneralCardBG = Color(0xFF383838)

val ShimmerLightGray = Color(0xFFF1F1F1)

val DoneGreen = Color(0xFF01C362)

val Card1Background = Color(0xFFFFF2E9)
val Card1BackgroundD = Color(0xCCFFF2E9)
val Card2Background = Color(0xFFE7F3FE)
val Card2BackgroundD = Color(0xCCE7F3FE)
val Card3Background = Color(0xFFF3EFFE)
val Card3BackgroundD = Color(0xCCF3EFFE)
val Card4Background = Color(0xFFDEFDED)
val Card4BackgroundD = Color(0xCCDEFDED)

val PrimeColor = Color(0xFF017EFF)
val SecondColor = Color(0xFFE5F3FE)
val SecondColorDark = Color(0xDDE5F3FE)


val StableBlack = Color(0xFF000000)
val StableWhite = Color(0xFFFFFFFF)
val NewAppointmentBack = Color(0xAAE5F3FE)
val NewAppointmentCard = Color(0xFF000F11)


val homeTopIconBG = Color(0xFFE5F3FE)
val TopAppBarCDark = Color(0xFF121212)


val Colors.doneGreen
    @Composable
    get() = if (isLight) DoneGreen else DoneGreen

val Colors.card1Background
    @Composable
    get() = if (isLight) Card1Background else Card1BackgroundD

val Colors.card4Background
    @Composable
    get() = if (isLight) Card4Background else Card4BackgroundD

val Colors.card3Background
    @Composable
    get() = if (isLight) Card3Background else Card3BackgroundD

val Colors.card2Background
    @Composable
    get() = if (isLight) Card2Background else Card2BackgroundD

val Colors.stableBlack
    @Composable
    get() = if (isLight) StableBlack else StableBlack

val Colors.topAppBarC
    @Composable
    get() = if (isLight) StableWhite else TopAppBarCDark

val Colors.generalCardBG
    @Composable
    get() = if (isLight) StableWhite else GeneralCardBG

val Colors.medicineCardBG
    @Composable
    get() = if (isLight) Card3Background else GeneralCardBG

val Colors.primeColor
    @Composable
    get() = if (isLight) PrimeColor else PrimeColor

val Colors.secondColor
    @Composable
    get() = if (isLight) SecondColor else SecondColorDark

val Colors.newAppointmentBack
    @Composable
    get() = if (isLight) SecondColor else NewAppointmentBack

val Colors.newAppointmentCard
    @Composable
    get() = if (isLight) StableWhite else NewAppointmentCard

val Colors.welcomeScreenBackgroundColor
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.descriptionColor
    @Composable
    get() = if (isLight) DarkGray.copy(alpha = 0.5f)
    else LightGray.copy(alpha = 0.5f)

val Colors.activeIndicatorColor
    @Composable
    get() = if (isLight) Purple500 else Purple700

val Colors.inactiveIndicatorColor
    @Composable
    get() = if (isLight) LightGray else DarkGray

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) Purple500 else Color.Black