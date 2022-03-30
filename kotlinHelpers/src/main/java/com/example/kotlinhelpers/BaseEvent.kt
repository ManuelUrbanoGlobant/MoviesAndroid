package com.example.kotlinhelpers

interface BaseEvent {
    object Init : BaseEvent
    object Loading : BaseEvent
    data class Error(val message: String) : BaseEvent
}
