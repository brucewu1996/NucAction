package com.example.nucaction.camera

import java.nio.ByteBuffer

interface FrameProcessor {

    /** Processes the input frame with the underlying detector.  */
    fun process(data: ByteBuffer, frameMetadata: FrameMetadata, graphicOverlay: GraphicOverlay)

    /** Stops the underlying detector and release resources.  */
    fun stop()
}
