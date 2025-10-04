package org.example.kobwebemptyproject.view_models

import kotlinx.coroutines.delay
import org.example.kobwebemptyproject.error_handling.IErrorHandlerBroadcastService
import org.example.kobwebemptyproject.error_handling.MessageResourceIdWrapper

class MainViewModel (
    private val broadcastService: IErrorHandlerBroadcastService
) : BaseViewModel<MainViewModel.Model, MainViewModel.Event>(
    model = Model(
        messageResourceIdWrapper = null
    )
) {
    suspend fun collectMessageResourceIdWrapper() {
        broadcastService.messageResourceIdWrapper.collect { messageResourceIdWrapper ->
            updateModelState { model ->
                model.copy(messageResourceIdWrapper = messageResourceIdWrapper)
            }
            console.log("ToastMessage", "messageResourceIdWrapper: $messageResourceIdWrapper")
        }
    }
    suspend fun startProcessNextMessageLoop() {
        while (true) {
            broadcastService.processNextMessage()
            console.log("ToastMessage", "MainViewModel processNextMessage() called")
            delay(3000L)
        }
    }

    data class Model(
        val messageResourceIdWrapper: MessageResourceIdWrapper?
    )

    sealed interface Event {
        data object CollectMessageResourceIdWrapper: Event
        data object StartProcessNextMessageLoop: Event
        data object LogOut : Event
    }

    override suspend fun processEvent(event: Event) {
        when (event) {
            Event.LogOut -> {
                console.log("ToastMessage", "MainViewModel LogOut event called")
            }
            Event.CollectMessageResourceIdWrapper -> {
                collectMessageResourceIdWrapper()
            }
            Event.StartProcessNextMessageLoop -> {
                startProcessNextMessageLoop()
            }
        }
    }
}