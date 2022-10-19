package com.example.dr_tryaq.util

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.dr_tryaq.domain.model.models.*
import com.example.dr_tryaq.presentation.common.EmptyScreen
import com.example.dr_tryaq.presentation.components.ShimmerEffect

@Composable
fun handleDoctorsResult(
    doctor: LazyPagingItems<Doctor>
): Boolean {
    doctor.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }
        return when {
            loadState.refresh is LoadState.Loading -> {
                false
            }
            error != null -> {
                EmptyScreen(error = error)
                false
            }
            else -> true
        }
    }
}

    @Composable
    fun handleAdsResult(
        ads: LazyPagingItems<Ad>
    ): Boolean {
        ads.apply {
            val error = when {
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                else -> null
            }

            return when {
                loadState.refresh is LoadState.Loading -> {
                  //  ShimmerEffect()
                    false
                }
                error != null -> {
                //    EmptyScreen(error = error)
                    false
                }
                else -> true
            }
        }
    }

    @Composable
    fun handleMedicinesResult(
        medicines: LazyPagingItems<Medicine>
    ): Boolean {
        medicines.apply {
            val error = when {
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                else -> null
            }

            return when {
                loadState.refresh is LoadState.Loading -> {
                    //ShimmerEffect()
                    false
                }
                error != null -> {
                    EmptyScreen(error = error)
                    false
                }
                else -> true
            }
        }
    }
    @Composable
    fun handleDiagnosisResult(
        diagnosis: LazyPagingItems<Diagnosis>
    ): Boolean {
        diagnosis.apply {
            val error = when {
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                else -> null
            }

            return when {
                loadState.refresh is LoadState.Loading -> {
                    ShimmerEffect()
                    false
                }
                error != null -> {
                    EmptyScreen(error = error)
                    false
                }
                else -> true
            }
        }
    }
@Composable
    fun handleServicesResult(
        services: LazyPagingItems<Service>
    ): Boolean {
        services.apply {
            val error = when {
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                else -> null
            }

            return when {
                loadState.refresh is LoadState.Loading -> {
                    ShimmerEffect()
                    false
                }
                error != null -> {
                    EmptyScreen(error = error)
                    false
                }
                else -> true
            }
        }
    }

    @Composable
    fun handleDepartmentsResult(
        departments: LazyPagingItems<Department>
    ): Boolean {
        departments.apply {
            val error = when {
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                else -> null
            }

            return when {
                loadState.refresh is LoadState.Loading -> {
                 //   ShimmerEffect()
                    false
                }
                error != null -> {
                   // EmptyScreen(error = error)
                    false
                }
                else -> true
            }
        }
    }

    @Composable
    fun handleAppointmentsResult(
        appointments: LazyPagingItems<Appointment>
    ): Boolean {
        appointments.apply {
            val error = when {
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                else -> null
            }

            return when {
                loadState.refresh is LoadState.Loading -> {
                   // ShimmerEffect()
                    false
                }
                error != null -> {
                   //     EmptyScreen(error = error)
                    false
                }
                else -> true
            }
        }
    }
    @Composable
    fun handleBaseResponseResult(
        baseResponse: LazyPagingItems<BaseResponse>
    ): Boolean {
        baseResponse.apply {
            val error = when {
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                else -> null
            }

            return when {
                loadState.refresh is LoadState.Loading -> {
                   // ShimmerEffect()
                    false
                }
                error != null -> {
                   //     EmptyScreen(error = error)
                    false
                }
                else -> true
            }
        }
    }

    @Composable
    fun handleHomeResult(
        ads: LazyPagingItems<Ad>,
        departments: LazyPagingItems<Department>,
    ): Boolean {
           val ad = handleAdsResult(ads = ads)
           val department = handleDepartmentsResult(departments = departments)
           return when (
               ad && department
           ) {
               true -> { true }
               false -> {
                   false}
           }
    }
