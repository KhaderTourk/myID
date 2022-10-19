package com.example.dr_tryaq.domain.model

import androidx.annotation.DrawableRes
import com.example.dr_tryaq.R

sealed class OnBoardingPage (
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.welcome1,
        title = "عن الدائرة الطبية",
        description = "الدائرة الطبية بالجامعة الإسلامية هي إحدى مراكز الجامعة التي تقدم خدماتها للطلاب والعاملين والخريجين."
    )

    object Second : OnBoardingPage(
        image = R.drawable.welcome2,
        title = "خدمات الدائرة",
        description = " الدائرة الطبية  تقدم خدمات الرعاية الصحية الأولية على أسس علمية متطورة  في الجامعة من أجل الحفاظ على بيئة صحية."
    )

    object Third : OnBoardingPage(
        image = R.drawable.welcome3,
        title = "نظرة مستقبلية",
        description = "تطوير بيئة صحية جامعية مجتمعية سليمة من خلال المحافظة على صحة الطلاب والعاملين والبيئة الجامعية."
    )
}
