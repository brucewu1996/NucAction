// Generated by data binding compiler. Do not edit!
package com.google.mlkit.md.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.mlkit.md.R;
import com.google.mlkit.md.camera.CameraSourcePreview;
import com.google.mlkit.md.camera.WorkflowModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityLiveBarcodeBinding extends ViewDataBinding {
  @NonNull
  public final CameraSourcePreview cameraPreview;

  @Bindable
  protected WorkflowModel mViewmodel;

  protected ActivityLiveBarcodeBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CameraSourcePreview cameraPreview) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cameraPreview = cameraPreview;
  }

  public abstract void setViewmodel(@Nullable WorkflowModel viewmodel);

  @Nullable
  public WorkflowModel getViewmodel() {
    return mViewmodel;
  }

  @NonNull
  public static ActivityLiveBarcodeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_live_barcode, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityLiveBarcodeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityLiveBarcodeBinding>inflateInternal(inflater, R.layout.activity_live_barcode, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityLiveBarcodeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_live_barcode, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityLiveBarcodeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityLiveBarcodeBinding>inflateInternal(inflater, R.layout.activity_live_barcode, null, false, component);
  }

  public static ActivityLiveBarcodeBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityLiveBarcodeBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityLiveBarcodeBinding)bind(component, view, R.layout.activity_live_barcode);
  }
}