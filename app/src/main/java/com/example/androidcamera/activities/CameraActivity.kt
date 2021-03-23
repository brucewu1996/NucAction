package com.example.androidcamera.activities

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.androidcamera.R
import com.example.androidcamera.databinding.CameraLayoutBinding
//import kotlinx.android.synthetic.main.camera_layout.*
import java.io.File

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: CameraLayoutBinding

    //constant
    companion object {
        private val IMAGE_CHOOSE = 1000;
        private val PERMISSION_CODE = 1001;
        private val TAKE_PIC = 1002 ;
        private lateinit var filePhoto: File
        var image_uri: Uri? = null
    }

    //@RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.camera_layout)

        binding = CameraLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTakePhoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA)==PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else{
                    openCamera();
                }
            }else{
                openCamera();
            }

        }

        binding.btnChoosePhoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else{
                    chooseImageGallery();
                }
            }else{
                chooseImageGallery();
            }

        }
    }

    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_CHOOSE)
    }

    private fun openCamera(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(image_uri != null){
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
            startActivityForResult(cameraIntent, TAKE_PIC)
        }else{
            Toast.makeText(this,"連個相機都不會寫，可憐啊",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val permission = permissions[0]
        when(requestCode){
            PERMISSION_CODE -> {
                Toast.makeText(this,"permission of request is $permission",Toast.LENGTH_SHORT).show()
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (permission == "android.permission.CAMERA"){
                        openCamera()
                    }else{
                        chooseImageGallery()
                    }
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //take picture
        if(requestCode == TAKE_PIC && resultCode == Activity.RESULT_OK){
            val takenPhoto = BitmapFactory.decodeFile(filePhoto.absolutePath)
            binding.viewImage.setImageBitmap(takenPhoto)
        }
        //choose picture from album
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }

        if(requestCode == TAKE_PIC || requestCode == IMAGE_CHOOSE && resultCode == Activity.RESULT_OK){
            //var imageUri = data.data
            binding.viewImage.setImageURI(data?.data)
        }else{
            Toast.makeText(this,"連個相機都不會寫，可憐啊",Toast.LENGTH_SHORT).show()
        }

    }
}
