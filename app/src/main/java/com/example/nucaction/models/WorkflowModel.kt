package com.example.nucaction.models

import android.app.Application
import android.content.Context
import androidx.annotation.MainThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.nucaction.product.Product
import com.example.nucaction.utils.PreferenceUtils
import com.google.mlkit.vision.barcode.Barcode
import java.util.HashSet

/** View model for handling application workflow based on NucAction.camera preview.  */
class WorkflowModel(application: Application) : AndroidViewModel(application) {

    val workflowState = MutableLiveData<WorkflowState>()
    val objectToSearch = MutableLiveData<DetectedObjectInfo>()
    val searchedObject = MutableLiveData<SearchedObject>()
    val detectedBarcode = MutableLiveData<Barcode>()

    private val objectIdsToSearch = HashSet<Int>()

    var isCameraLive = false
        private set

    private var confirmedObject: DetectedObjectInfo? = null

    private val context: Context
        get() = getApplication<Application>().applicationContext

    /**
     * State set of the application workflow.
     */
    enum class WorkflowState {
        NOT_STARTED,
        DETECTING,
        DETECTED,
        CONFIRMING,
        CONFIRMED,
        SEARCHING,
        SEARCHED,
        RECORD_BY_USER,
        RECORDED
    }

    @MainThread
    fun setWorkflowState(workflowState: WorkflowState) {
        if (workflowState != WorkflowState.CONFIRMED &&
            workflowState != WorkflowState.SEARCHING &&
            workflowState != WorkflowState.SEARCHED
        ) {
            confirmedObject = null
        }
        this.workflowState.value = workflowState
    }

    @MainThread
    fun confirmingObject(confirmingObject: DetectedObjectInfo, progress: Float) {
        val isConfirmed = progress.compareTo(1f) == 0
        if (isConfirmed) {
            confirmedObject = confirmingObject
            if (PreferenceUtils.isAutoSearchEnabled(context)) {
                setWorkflowState(WorkflowState.SEARCHING)
                triggerSearch(confirmingObject)
            } else {
                setWorkflowState(WorkflowState.CONFIRMED)
            }
        } else {
            setWorkflowState(WorkflowState.CONFIRMING)
        }
    }

    @MainThread
    fun onSearchButtonClicked() {
        confirmedObject?.let {
            setWorkflowState(WorkflowState.SEARCHING)
            triggerSearch(it)
        }
    }

    private fun triggerSearch(detectedObject: DetectedObjectInfo) {
        val objectId = detectedObject.objectId ?: throw NullPointerException()
        if (objectIdsToSearch.contains(objectId)) {
            // Already in searching.
            return
        }
        objectIdsToSearch.add(objectId)
        objectToSearch.value = detectedObject
    }

    fun markCameraLive() {
        isCameraLive = true
        objectIdsToSearch.clear()
    }

    fun markCameraFrozen() {
        isCameraLive = false
    }

    fun onSearchCompleted(detectedObject: DetectedObjectInfo, products: List<Product>) {
        val lConfirmedObject = confirmedObject
        if (detectedObject != lConfirmedObject) {
            // Drops the search result from the object that has lost focus.
            return
        }
        objectIdsToSearch.remove(detectedObject.objectId)
        setWorkflowState(WorkflowState.SEARCHED)
        searchedObject.value = SearchedObject(context.resources, lConfirmedObject, products)
    }
}
