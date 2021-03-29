package com.example.nucaction.barcode

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.nucaction.R
import com.example.nucaction.models.WorkflowModel

class InputNutritionInfo : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflater: LayoutInflater = this.getLayoutInflater()
        val view: View = inflater.inflate(R.layout.input_nutrition_info, null)
        val confirm_btn: Button = view.findViewById(R.id.input_nutrition_confirmed_btn)

        confirm_btn.setOnClickListener {
            dismiss()
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    override fun onDismiss(dialogInterface: DialogInterface) {
        activity?.let {
            // Back to working state after the bottom sheet is dismissed.
            ViewModelProviders.of(it).get(WorkflowModel::class.java).setWorkflowState(WorkflowModel.WorkflowState.DETECTING)
        }
        super.onDismiss(dialogInterface)
    }
    companion object {
        private const val TAG = "InputNutritionInfo"
        val nutritionInfo = InputNutritionInfo()
        fun show(supportFragmentManager: FragmentManager) {
            nutritionInfo.show(supportFragmentManager, TAG)
        }
    }
}