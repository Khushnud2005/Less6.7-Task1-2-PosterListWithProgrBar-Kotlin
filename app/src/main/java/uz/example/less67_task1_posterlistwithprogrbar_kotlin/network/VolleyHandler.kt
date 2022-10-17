package uz.example.less67_task1_posterlistwithprogrbar_kotlin.network

interface VolleyHandler {
    fun onSuccess(response: String?)
    fun onError(error: String?)
}