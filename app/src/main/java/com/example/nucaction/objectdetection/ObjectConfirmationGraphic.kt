package com.example.nucaction.objectdetection

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.content.ContextCompat
import com.example.nucaction.camera.GraphicOverlay
import com.example.nucaction.R
import com.example.nucaction.utils.PreferenceUtils

class ObjectConfirmationGraphic internal constructor(
    overlay: GraphicOverlay,
    private val confirmationController: ObjectConfirmationController
) : GraphicOverlay.Graphic(overlay) {

    private val outerRingFillPaint: Paint
    private val outerRingStrokePaint: Paint
    private val innerRingPaint: Paint
    private val progressRingStrokePaint: Paint
    private val outerRingFillRadius: Int
    private val outerRingStrokeRadius: Int
    private val innerRingStrokeRadius: Int

    init {

        val resources = overlay.resources
        outerRingFillPaint = Paint().apply {
            style = Paint.Style.FILL
            color = ContextCompat.getColor(context, R.color.object_reticle_outer_ring_fill)
        }

        outerRingStrokePaint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = resources.getDimensionPixelOffset(R.dimen.object_reticle_outer_ring_stroke_width).toFloat()
            strokeCap = Paint.Cap.ROUND
            color = ContextCompat.getColor(context, R.color.object_reticle_outer_ring_stroke)
        }

        progressRingStrokePaint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = resources.getDimensionPixelOffset(R.dimen.object_reticle_outer_ring_stroke_width).toFloat()
            strokeCap = Paint.Cap.ROUND
            color = ContextCompat.getColor(context, R.color.white)
        }

        innerRingPaint = Paint()
        if (PreferenceUtils.isMultipleObjectsMode(overlay.context)) {
            innerRingPaint.style = Paint.Style.FILL
            innerRingPaint.color = ContextCompat.getColor(context, R.color.object_reticle_inner_ring)
        } else {
            innerRingPaint.style = Paint.Style.STROKE
            innerRingPaint.strokeWidth =
                resources.getDimensionPixelOffset(R.dimen.object_reticle_inner_ring_stroke_width).toFloat()
            innerRingPaint.strokeCap = Paint.Cap.ROUND
            innerRingPaint.color = ContextCompat.getColor(context, R.color.white)
        }

        outerRingFillRadius = resources.getDimensionPixelOffset(R.dimen.object_reticle_outer_ring_fill_radius)
        outerRingStrokeRadius = resources.getDimensionPixelOffset(R.dimen.object_reticle_outer_ring_stroke_radius)
        innerRingStrokeRadius = resources.getDimensionPixelOffset(R.dimen.object_reticle_inner_ring_stroke_radius)
    }

    override fun draw(canvas: Canvas) {
        val cx = canvas.width / 2f
        val cy = canvas.height / 2f
        canvas.drawCircle(cx, cy, outerRingFillRadius.toFloat(), outerRingFillPaint)
        canvas.drawCircle(cx, cy, outerRingStrokeRadius.toFloat(), outerRingStrokePaint)
        canvas.drawCircle(cx, cy, innerRingStrokeRadius.toFloat(), innerRingPaint)

        val progressRect = RectF(
            cx - outerRingStrokeRadius,
            cy - outerRingStrokeRadius,
            cx + outerRingStrokeRadius,
            cy + outerRingStrokeRadius
        )
        val sweepAngle = confirmationController.progress * 360
        canvas.drawArc(
            progressRect,
            /* startAngle= */ 0f,
            sweepAngle,
            /* useCenter= */ false,
            progressRingStrokePaint
        )
    }
}