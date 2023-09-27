package com.alberto.studycompanion.detail.feynman.data

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


class VoiceToTextParser (
    private val app: Application
) : RecognitionListener {

    private val _state = MutableStateFlow(VoiceToTextState())
    val state = _state.asStateFlow()

    val recognizer = SpeechRecognizer.createSpeechRecognizer(app)

    var finalText : String = ""

    // Flag to control continuous listening
    private var isContinuousListening = false

    fun startListening(languageCode : String = "en"){
        _state.update { VoiceToTextState() }

        if(!SpeechRecognizer.isRecognitionAvailable(app)){
            _state.update {
                it.copy(
                    error = "Recognition is not available."
                )
            }
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode)
            putExtra(
                RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,5000
            )
        }

        recognizer.setRecognitionListener(this)
        recognizer.startListening(intent)

        _state.update {
            it.copy(
                isSpeaking = true
            )
        }
    }

    fun startContinuousListening(languageCode: String = "en") {
        isContinuousListening = true
        startListening(languageCode)
    }

    fun stopContinuousListening() {
        isContinuousListening = false
        stopListening()
    }

    fun stopListening(){
        _state.update {
            it.copy(
                isSpeaking = false
            )
        }
        recognizer.stopListening()
    }

    override fun onReadyForSpeech(params: Bundle?) {
        _state.update {
            it.copy(
                error = null
            )
        }
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(p0: Float) = Unit

    override fun onBufferReceived(p0: ByteArray?) = Unit

    override fun onEndOfSpeech() {
        if (isContinuousListening) {
            startListening()
        }
    }

    override fun onError(error: Int) {
        if (error == SpeechRecognizer.ERROR_CLIENT){
            return
        }
        _state.update {
            it.copy(
                error = "Error: $error"
            )
        }
    }

    override fun onResults(results: Bundle?) {

        _state.update {
            it.copy(
                spokenText = finalText
            )
        }

        finalText = ""

    }

    override fun onPartialResults(results: Bundle?) {

        val partial = results
            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0) ?: ""

        finalText += partial

    }

    override fun onEvent(p0: Int, p1: Bundle?) = Unit
}


data class VoiceToTextState(
    val spokenText : String = "",
    val isSpeaking : Boolean = false,
    val error : String? = null
)
